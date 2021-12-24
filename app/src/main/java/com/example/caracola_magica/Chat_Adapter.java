package com.example.caracola_magica;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caracola_magica.databinding.ChatViewItemBinding;

public class Chat_Adapter extends ListAdapter<Chat_Bot, Chat_Adapter.ChatViewHolder> {

    public static final DiffUtil.ItemCallback<Chat_Bot> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Chat_Bot>() {
                @Override
                public boolean areItemsTheSame(@NonNull Chat_Bot oldItem, @NonNull Chat_Bot newItem) {
                    return oldItem.getPregunta().equals(newItem.getPregunta());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Chat_Bot oldItem, @NonNull Chat_Bot newItem) {
                    return oldItem.equals(newItem);
                }
            };

    protected Chat_Adapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public Chat_Adapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatViewItemBinding binding = ChatViewItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ChatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Chat_Adapter.ChatViewHolder holder, int position) {
        Chat_Bot chat = getItem(position);

        holder.bind(chat);
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        private ChatViewItemBinding binding;

        public ChatViewHolder(@NonNull ChatViewItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }

        @SuppressLint("ResourceAsColor")
        public void bind(Chat_Bot chat){
            if (chat.getQuestion()){
                // es texto ingresado por el usuario
                binding.txtResAnswer.setBackgroundColor(Color.parseColor("#A8E3D3"));
                binding.txtResAnswer.setGravity(Gravity.END);
            }else {
                // es la respuesta de la app
                binding.txtResAnswer.setBackgroundColor(Color.parseColor("#EFF7F6"));
                binding.txtResAnswer.setGravity(Gravity.START);
            }

            binding.txtResAnswer.setText(chat.getPregunta());
            binding.executePendingBindings();
        }
    }
}
