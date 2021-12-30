package project.stn991516745.final_project_modaniel.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync
import project.stn991516745.final_project_modaniel.R
import project.stn991516745.final_project_modaniel.databinding.FragmentHomeBinding
import project.stn991516745.final_project_modaniel.dbase.*
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class HomeFragment : Fragment() , AdapterView.OnItemSelectedListener  {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mDb:ExerciseDatabase
    private var dbFlag=true // true means use free weights false means use cycling

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mDb = ExerciseDatabase.getInstance((requireActivity()))
        val textView: TextView = binding.textHome
        val spinner: Spinner =binding.spinner
        val submitButton : Button = binding.submit
        var nameField : EditText = binding.editTextPersonName
        var numField1 : EditText =binding.editTextNumber//refers to max weight or distance
        var numDecimal: EditText =binding.editTextNumberDecimal
        var numField2 : EditText=binding.editTextNumber2
        var buttonDelCycling=binding.deleteCycles
        var buttonDelWeights=binding.deleteWeights
        buttonDelWeights.setOnClickListener{
            doAsync {
                val list = mDb.excerciseDao().getAllWeights()
                val delete = mDb.excerciseDao().deleteAllWeights(list)  // Get the student list from database
            }

        }
        buttonDelCycling.setOnClickListener{
            doAsync {
                val list = mDb.excerciseDao().getAllCycle()
                val delete = mDb.excerciseDao().deleteAllCyle(list)  // Get the student list from database
            }

        }


                activity?.let {
           ArrayAdapter.createFromResource(
                it,
                R.array.selections,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
               spinner.adapter = adapter
            }

        }
        spinner.onItemSelectedListener = this
        submitButton.setOnClickListener{
            var name=""
            var num1=0
            var num2=0
            var decimal=0.0
            if(nameField.text.isNotEmpty()){
                name= nameField.text.toString()
            }
            if(numField1.text.isNotEmpty()){
                num1=numField1.text.toString().toInt()
            }
            if(numField2.text.isNotEmpty()){
                num2=numField2.text.toString().toInt()
            }
            if(numDecimal.text.isNotEmpty()){
                decimal=numDecimal.text.toString().toDouble()
            }
            var cyclingObject=Cycling(0,name,num1,decimal,num2)
            var weightsObject=FreeWeights(0,name,num1,decimal,num2)
            doAsync{
                if(dbFlag) mDb.excerciseDao().insertWeights(weightsObject)
                else mDb.excerciseDao().insertCycle(cyclingObject)
                Log.d("DEBUG",mDb.excerciseDao().getAllWeights().count().toString())
            }

        }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        dbFlag = pos==0
        Log.d("TEST FLAG", dbFlag.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}