package project.stn991516745.final_project_modaniel.ui.weights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cycling.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import project.stn991516745.final_project_modaniel.databinding.FragmentWeightsBinding
import project.stn991516745.final_project_modaniel.dbase.ExerciseDatabase
import project.stn991516745.final_project_modaniel.dbase.FreeWeights

class WeightsFragment : Fragment() {

    private lateinit var weightsViewModel: WeightsViewModel
    private var _binding: FragmentWeightsBinding? = null
    private lateinit var mDb: ExerciseDatabase
    private var fullList=listOf<FreeWeights>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weightsViewModel =
            ViewModelProvider(this).get(WeightsViewModel::class.java)

        _binding = FragmentWeightsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textSlideshow
        mDb = ExerciseDatabase.getInstance((requireActivity()))
        doAsync{
            fullList=mDb.excerciseDao().getAllWeights()
            uiThread{
                recycleView.adapter = WeightsRecycler(fullList)
                recycleView.layoutManager= LinearLayoutManager(it.context)
                recycleView.setHasFixedSize(true)
            }
        }


        weightsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}