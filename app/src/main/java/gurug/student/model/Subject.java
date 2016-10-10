package gurug.student.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import gurug.student.database.DataBaseQuizUp;

/**
 * Created by moin on 4/10/16.
 */
@Table(database = DataBaseQuizUp.class)
public class Subject extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String subjectName;

    @Column
    public String level;

    @Column
    public String points;

    @Column
    public String color;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
