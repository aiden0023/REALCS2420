package assign02;

import java.util.ArrayList;
import java.util.HashMap;

public class CS2420Student extends UofUStudent {
    private EmailAddress contactInfo;
    private ArrayList<Double> assignment = new ArrayList<>();
    private ArrayList<Double> exam = new ArrayList<>();
    private ArrayList<Double> lab = new ArrayList<>();
    private ArrayList<Double> quiz = new ArrayList<>();
    //NOTE: change scores from ArrayList to HashMap if have time

    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
    }

    public EmailAddress getContactInfo() {
        return this.contactInfo;
    }

    public void addScore(double score, String category) {
        switch (category) {
            case "assignment" -> assignment.add(score);
            case "exam" -> exam.add(score);
            case "lab" -> lab.add(score);
            case "quiz" -> quiz.add(score);
        }
    }

    public double computeFinalScore() {
        double assignmentAverage = this.calculateAverage(assignment);
        double examAverage = this.calculateAverage(exam);
        double labAverage = this.calculateAverage(lab);
        double quizAverage = this.calculateAverage(quiz);

        if (assignment.isEmpty() || exam.isEmpty() || lab.isEmpty() || quiz.isEmpty()) {
            return 0.0;
        }

        if (examAverage < 65.0) {
            return examAverage;
        }

        return ((assignmentAverage * 35.0) + (examAverage * 45.0) + (labAverage * 10.0) + (quizAverage * 10.0)) / 100.0;
    }

    public String computeFinalGrade() {
        double finalScore = this.computeFinalScore();

        if (assignment.isEmpty() || exam.isEmpty() || lab.isEmpty() || quiz.isEmpty()) {
            return "N/A";
        }

        if (finalScore <= 100 && finalScore >= 93) {
            return "A";
        } else if (finalScore < 93 && finalScore >= 90) {
            return "A-";
        } else if (finalScore < 90 && finalScore >= 87) {
            return "B+";
        } else if (finalScore < 87 && finalScore >= 83) {
            return "B";
        } else if (finalScore < 83 && finalScore >= 80) {
            return "B-";
        } else if (finalScore < 80 && finalScore >= 77) {
            return "C+";
        } else if (finalScore < 77 && finalScore >= 73) {
            return "C";
        } else if (finalScore < 73 && finalScore >= 70) {
            return "C-";
        } else if (finalScore < 70 && finalScore >= 67) {
            return "D+";
        } else if (finalScore < 67 && finalScore >= 63) {
            return "D";
        } else if (finalScore < 63 && finalScore >= 60) {
            return "D-";
        } else if (finalScore < 60 && finalScore >= 0) {
            return "E";
        } else {
            return "N/A";
        }
    }

    //HELPER METHODS
    private double calculateAverage(ArrayList<Double> category) {
        double average = 0;
        double count = 0;
        for (double score : category) {
            average += score;
            count++;
        }

        return average / count;
    }

    public ArrayList<Double> getAssignment() {
        return assignment;
    }

    public void setAssignment(ArrayList<Double> assignment) {
        this.assignment = assignment;
    }

    public ArrayList<Double> getExam() {
        return exam;
    }

    public void setExam(ArrayList<Double> exam) {
        this.exam = exam;
    }

    public ArrayList<Double> getLab() {
        return lab;
    }

    public void setLab(ArrayList<Double> lab) {
        this.lab = lab;
    }

    public ArrayList<Double> getQuiz() {
        return quiz;
    }

    public void setQuiz(ArrayList<Double> quiz) {
        this.quiz = quiz;
    }
}
