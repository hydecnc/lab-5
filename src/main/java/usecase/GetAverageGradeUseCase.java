package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        final String[] team_names = team.getMembers();
        for (int i = 0; i < team_names.length; i++){
            Grade[] grades = this.gradeDataBase.getGrades(team_names[i]);
            for (int j = 0; j < grades.length; j++){
                if (grades[j].getCourse().equals(course)){
                    sum += grades[j].getGrade();
                    count += 1;
                }
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
