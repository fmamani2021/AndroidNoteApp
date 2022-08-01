package com.demo.notesapp.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.demo.notesapp.interfaces.OnNoteChecked;
import com.demo.notesapp.models.Note;
import com.demo.notesapp.R;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private List<Note> listNotes;
    private final Context context;
    private LayoutInflater layoutInflater;
    private OnNoteChecked onNoteChecked;

    public NotesAdapter(Context context, OnNoteChecked onNoteChecked, List<Note> listNotes)
    {
        this.onNoteChecked = onNoteChecked;
        this.setListNotes(listNotes);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getListNotes().size();
    }

    @Override
    public Object getItem(int position) {
        return getListNotes().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderNote holder;

        if(view == null){
            holder = new ViewHolderNote();
            view = layoutInflater.inflate(R.layout.item_note, viewGroup, false);
            holder.txtTitle = (TextView)view.findViewById(R.id.txtTitle);
            holder.txtComment = (TextView)view.findViewById(R.id.txtComment);
            holder.txtCreatedDate = (TextView)view.findViewById(R.id.txtCreatedDate);
            holder.chBoxIsChecked = (CheckBox)view.findViewById(R.id.chBoxIsChecked);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolderNote)view.getTag();
        }

        Note itemNote = getListNotes().get(i);

        holder.txtTitle.setText(itemNote.getTitle());
        holder.txtComment.setText(Html.fromHtml(itemNote.getComment()));
        holder.txtCreatedDate.setText(String.format(itemNote.getCreateDate().toString(), "DD:MM:YYYY hh:mm"));
        holder.chBoxIsChecked.setChecked(itemNote.getChecked());

        holder.chBoxIsChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNote.setChecked(!itemNote.getChecked());
                onNoteChecked.anyChecked(AnyNoteChecked());
            }
        });

        return view;
    }

    public List<Note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(List<Note> listNotes) {
        this.listNotes = listNotes;
    }

    public Boolean AnyNoteChecked(){
        Boolean anyChecked = false;
        for (Note note: listNotes) {
            if(note.getChecked()){
                anyChecked = true;
            }
        }
        return anyChecked;
    }

    static class ViewHolderNote {
        private TextView txtTitle;
        private TextView txtComment;
        private TextView txtCreatedDate;
        private CheckBox chBoxIsChecked;
    }
}
