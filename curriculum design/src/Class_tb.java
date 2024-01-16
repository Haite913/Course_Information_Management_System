import java.util.Objects;

public class Class_tb {
   private String 记录号;
   private String 课程号;
   private String 课程名称;
   private float 学分;
   private String 教室;
   private String 考试时间;
   private int 成绩;

    @Override
    public String toString() {
        return "Class{" +
                "记录号='" + 记录号 + '\'' +
                ", 课程号='" + 课程号 + '\'' +
                ", 课程名称='" + 课程名称 + '\'' +
                ", 学分=" + 学分 +
                ", 教室='" + 教室 + '\'' +
                ", 考试时间='" + 考试时间 + '\'' +
                ", 成绩=" + 成绩 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class_tb aClass = (Class_tb) o;
        return Float.compare(aClass.学分, 学分) == 0 && 成绩 == aClass.成绩 && Objects.equals(记录号, aClass.记录号) && Objects.equals(课程号, aClass.课程号) && Objects.equals(课程名称, aClass.课程名称) && Objects.equals(教室, aClass.教室) && Objects.equals(考试时间, aClass.考试时间);
    }

    @Override
    public int hashCode() {
        return Objects.hash(记录号, 课程号, 课程名称, 学分, 教室, 考试时间, 成绩);
    }

    public String get记录号() {
        return 记录号;
    }

    public void set记录号(String 记录号) {
        this.记录号 = 记录号;
    }

    public String get课程号() {
        return 课程号;
    }

    public void set课程号(String 课程号) {
        this.课程号 = 课程号;
    }

    public String get课程名称() {
        return 课程名称;
    }

    public void set课程名称(String 课程名称) {
        this.课程名称 = 课程名称;
    }

    public float get学分() {
        return 学分;
    }

    public void set学分(float 学分) {
        this.学分 = 学分;
    }

    public String get教室() {
        return 教室;
    }

    public void set教室(String 教室) {
        this.教室 = 教室;
    }

    public String get考试时间() {
        return 考试时间;
    }

    public void set考试时间(String 考试时间) {
        this.考试时间 = 考试时间;
    }

    public int get成绩() {
        return 成绩;
    }

    public void set成绩(int 成绩) {
        this.成绩 = 成绩;
    }

    public Class_tb() {
    }

    public Class_tb(String 记录号, String 课程号, String 课程名称, float 学分, String 教室, String 考试时间, int 成绩) {
        this.记录号 = 记录号;
        this.课程号 = 课程号;
        this.课程名称 = 课程名称;
        this.学分 = 学分;
        this.教室 = 教室;
        this.考试时间 = 考试时间;
        this.成绩 = 成绩;
    }
}
