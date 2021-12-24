package com.example.caracola_magica;

import java.util.Objects;

public class Chat_Bot {


    private final Boolean isQuestion;
    private String pregunta;
    private final String[] chatRes = {"Si.", "No.", "Pregunta de nuevo.", "Es muy probable.", "No lo creo.", "No se.", "Tal vez.", "Por supuesto."};

    public Chat_Bot(Boolean isQuestion, String pregunta) {
        this.isQuestion = isQuestion;
        this.pregunta = pregunta;
        if (!isQuestion){
            res();
        }
    }

    public Boolean getQuestion() {
        return isQuestion;
    }

    public String getPregunta() {
        return pregunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat_Bot chat_bot = (Chat_Bot) o;
        return isQuestion.equals(chat_bot.isQuestion) && pregunta.equals(chat_bot.pregunta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isQuestion, pregunta);
    }

    public void res(){
        pregunta = chatRes[(int) (Math.random()*chatRes.length) ];
    }
}
