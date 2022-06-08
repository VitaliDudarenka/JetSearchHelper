package com.dudarenkavitali.jetsearchhelper.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dudarenkavitali.jetsearchhelper.R
import com.dudarenkavitali.jetsearchhelper.databinding.DialogMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider
import kotlin.properties.Delegates

private const val LATITUDE = "latitude"
private const val LONGITUDE = "longitude"

class MapDialog : DialogFragment() {

    private val binding: DialogMapBinding by viewBinding(CreateMethod.INFLATE)
    private var latitude by Delegates.notNull<Double>()
    private var longitude by Delegates.notNull<Double>()

    companion object {
        fun newInstance(latitude: Double, longitude: Double): MapDialog {
            val fragment = MapDialog()
            fragment.arguments = bundleOf(LATITUDE to latitude, LONGITUDE to longitude)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
        showsDialog = true
        MapKitFactory.initialize(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        latitude = requireArguments().get(LATITUDE) as Double
        longitude = requireArguments().get(LONGITUDE) as Double

        binding.mapView.map.move(
            CameraPosition(Point(latitude, longitude), 8.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )

        val markerView = View(requireContext()).apply {
            background = getDrawable(requireContext(), R.drawable.ic_plane)
        }
        binding.mapView.map.mapObjects.addPlacemark(
            Point(latitude, longitude), ViewProvider(markerView)
        )

    }

    override fun onStart() {
        super.onStart()
        requireDialog().setCancelable(true)
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requireDialog().window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

}