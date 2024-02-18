package edu.csuglobal.csc475.gpstracker

import android.location.Location
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(private val locations: MutableList<Location>): RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtTime: TextView
        val txtLat: TextView
        val txtLong: TextView
        val txtAlt: TextView

        init {
            txtTime =  view.findViewById<TextView>(R.id.txtTime)
            txtLat = view.findViewById<TextView>(R.id.txtLat)
            txtLong = view.findViewById<TextView>(R.id.txtLong)
            txtAlt = view.findViewById<TextView>(R.id.txtAlt)
        }
    }//end ViewHolder class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.checkin, parent, false)

        return return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTime.text = "${locations[position].time}"
        holder.txtLat.text = "${locations[position].latitude}"
        holder.txtLong.text = "${locations[position].longitude}"
        holder.txtAlt.text = "${locations[position].altitude}"
    }//end onBindHolder function

    fun clear() {
        locations.clear()
        notifyDataSetChanged()
    }//end clear function

    override fun getItemCount() = locations.size

    fun addLocation(location: Location) {
        locations.add(location)
        notifyDataSetChanged()
    }//end addLocation function
}//end LocationAdapter class
