package assign02;

import java.util.ArrayList;

public class CS2420Student extends UofUStudent {
    private EmailAddress contactInfo;
    private ArrayList<Double> assignment = new ArrayList<>();
    private ArrayList<Double> exam = new ArrayList<>();
    private ArrayList<Double> lab = new ArrayList<>();
    private ArrayList<Double> quiz = new ArrayList<>();

    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
    }

    /**
     * Gets email address of this student
     *
     * @return - email address of this student
     */
    public EmailAddress getContactInfo() {
        return this.contactInfo;
    }

    /**
     * Adds an assignment, exam, lab, or quiz score to this student.
     *
     * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz",
     * then this method has no effect.
     *
     * @param score - the score to be added
     * @param category - the category in which to add the score
     */
    public void addScore(double score, String category) {
        switch (category) { //no need for default case since default is to "do nothing"
            case "assignment":
                assignment.add(score);
                break;
            case "exam":
                exam.add(score);
                break;
            case "lab":
                lab.add(score);
                break;
            case "quiz":
                quiz.add(score);
                break;
        }
    }

    /**
     * Computes the final score of this student (taking into account weights of each category).
     *
     * @return - final score of this student
     */
    public double computeFinalScore() {
        //calculates score averages for each category
        double assignmentAverage = this.calculateAverage(assignment);
        double examAverage = this.calculateAverage(exam);
        double labAverage = this.calculateAverage(lab);
        double quizAverage = this.calculateAverage(quiz);

        if (assignment.isEmpty() || exam.isEmpty() || lab.isEmpty() || quiz.isEmpty()) { //checking for all categories to have at least one score
            return 0.0;
        }

        if (examAverage < 65.0) { //if exam average is below 65, return average
            return examAverage;
        }

        return ((assignmentAverage * 35.0) + (examAverage * 45.0) + (labAverage * 10.0) + (quizAverage * 10.0)) / 100.0;
    }

    /**
     * Computes the final grade of this student given the final score.
     *
     * @return - final grade of this student
     */
    public String computeFinalGrade() {
        //computes final score
        double finalScore = this.computeFinalScore();

        if (assignment.isEmpty() || exam.isEmpty() || lab.isEmpty() || quiz.isEmpty()) { //checking for all categories to have at least one score
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

    /**
     * Calculates score averages for a given grade category
     *
     * @param category - grade category wishing to average
     * @return - average of grade category
     */
    private double calculateAverage(ArrayList<Double> category) {
        double average = 0.0;
        double count = 0.0;
        for (double score : category) {
            average += score;
            count++;
        }

        return average / count;
    }

    public ArrayList<Double> getAssignment() {
        return assignment;
    }

    public ArrayList<Double> getExam() {
        return exam;
    }

    public ArrayList<Double> getLab() {
        return lab;
    }

    public ArrayList<Double> getQuiz() {
        return quiz;
    }
}
