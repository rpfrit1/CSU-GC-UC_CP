package edu.csuglobal.csc475.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.csuglobal.csc475.todo.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val todos: ArrayList<ToDoItem> = ArrayList()
    private val adapter: ToDoAdapter = ToDoAdapter(todos)
    <<<<<<< HEAD
    private val dm: DataManager = DataManager(todos)
    =======
    private val dm: DataManager = DataManager(todos)
    >>>>>>> origin/main
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }//end setOnClickListener function

    }//end onViewCreated function

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }//end onDestroyView function
}//end FirstFragment class