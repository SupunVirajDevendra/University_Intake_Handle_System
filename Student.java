class Student {
    private String studentID;
    private String studentName;
    private Module[] modules = new Module[3];

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        for (int i = 0; i < 3; i++) {
            modules[i] = new Module();
        }
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setModuleMark(int moduleIndex, int marks) {
        if (moduleIndex >= 0 && moduleIndex < 3) {
            modules[moduleIndex].setMarks(marks);
        }
    }

    public int[] getModuleMarks() {
        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            marks[i] = modules[i].getMarks();
        }
        return marks;
    }

    public String getGrade() {
        int totalMarks = 0;
        for (int i = 0; i < 3; i++) {
            totalMarks += modules[i].getMarks();
        }
        int average = totalMarks / 3;
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}

