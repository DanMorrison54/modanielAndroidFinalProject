package project.stn991516745.final_project_modaniel.ui.cycling

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
import project.stn991516745.final_project_modaniel.databinding.FragmentCyclingBinding
import project.stn991516745.final_project_modaniel.dbase.Cycling
import project.stn991516745.final_project_modaniel.dbase.ExerciseDatabase
import project.stn991516745.final_project_modaniel.dbase.FreeWeights
import project.stn991516745.final_project_modaniel.ui.weights.WeightsRecycler

class CyclingFragment : Fragment() {

    private lateinit var cyclingViewModel: CyclingViewModel
    private var _binding: FragmentCyclingBinding? = null
    private lateinit var mDb: ExerciseDatabase
    private var fullList=listOf<Cycling>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cyclingViewModel =
            ViewModelProvider(this).get(CyclingViewModel::class.java)

        _binding = FragmentCyclingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mDb = ExerciseDatabase.getInstance((requireActivity()))
        doAsync{
            fullList=mDb.excerciseDao().getAllCycle()
            uiThread{
                recycleView.adapter = CycleRecycler(fullList)
                recycleView.layoutManager= LinearLayoutManager(it.context)
                recycleView.setHasFixedSize(true)
            }
        }
        val textView: TextView = binding.textGallery
        cyclingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}