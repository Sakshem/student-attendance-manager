# Student-Attendance-Manager
* Built a Web Spring-boot Application that tracks student attendance. Allows teachers to record the attendance for a new day, view and update a student's past attendance by simplifying the User Interface
* Performs all the CRUD operations on the students records and stores it in a MySQL database
* Added Spring Security with JDBC authentication and deployed 3 different roles Student, Teacher and Admin with different capabilities
* Automated generation of student attendance records for each new date when selected through the filter
* Used Thymeleaf for the dynamic content of the web and HTML5, CSS for the static content of the web

### Here's how this project looks like -
![image](https://user-images.githubusercontent.com/61968230/166402894-7495652c-2b29-4cb9-8c50-f1d02b1c58af.png)


### Added a custom login page
![image](https://user-images.githubusercontent.com/61968230/166403073-a6157a4b-16e9-46c0-9761-e3f278c1860f.png)

**Provided 3 different roles with different access levels**
1) Role 1: Student
2) Role 2: Teacher
3) Role 3: Admin

**Role 1: Student**

Users with this role can only view the attendance on any past dates but keep track of their attendance. Students can use the filter to select date, class and section to check the records for that particular class on that particular date.

![image](https://user-images.githubusercontent.com/61968230/166403608-a4678c7e-0813-4e94-a3bd-0bd0a6fea77d.png)

**Role 2: Teacher** 

Users when logged in with this role can not only view the attendance but also can update any personal record of the student (still working on this). But can also mark student present or absent on any date, when user enter a new date in the filter that is not present in the database, it automatically creates the record of entire school's students for that particular date so any other teacher of any other class can directly just access the attendance records instead of them creating the records one more time. After that teacher can mark present or absent to any student of their class while calling out their name.

![image](https://user-images.githubusercontent.com/61968230/166404070-566969ab-362f-4410-9bf5-22a903f4a8b1.png)


**Role 3: Admin**

Users with those role also have access to both update and a delete button which can delete the records of any student in any class permanently across all the dates present in the database, they can also indeed mark student as present or absent and can view past records of the students using the search filter.

![image](https://user-images.githubusercontent.com/61968230/166404228-73a7c0c3-e2e5-4eab-9852-9c5d176affa5.png)

**Add Student button**

This button redirects to a form page where teacher or admin can add another student in a particular class and section and there records will automatically be generated for all the past dates present in the database.

![image](https://user-images.githubusercontent.com/61968230/166404368-024502fb-be60-439b-a731-747021e4fdf1.png)

**Security**

* As using Spring Security to prevent unauthorized access to the website, without proper authentication one cannot login or cannot access any web URL directly.
* One must authorize themselves to gain access to the content of the web page.
* Encrypted passwords are stored in the database using BCryptPasswordEncoder

**Added a logout button**

When clicked on the logout button on the top right corner of the navigation bar, user is redirected to the login page

![image](https://user-images.githubusercontent.com/61968230/166404766-9faa94e8-4c73-4280-b6a7-c6cf0fe6f9b8.png)


the login page:

![image](https://user-images.githubusercontent.com/61968230/166404547-380f38df-fe09-4b15-a80b-3015ef9b419a.png)




