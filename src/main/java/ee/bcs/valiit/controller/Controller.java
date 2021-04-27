package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson1;
import ee.bcs.valiit.tasks.Lesson2; // impordib, et kasutada teisest klassist
import ee.bcs.valiit.tasks.Lesson2b;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller { // klass suure algustähega

    public List<Employee> employeeList = new ArrayList<>(); // Defineerisin klassi sisse muutuja ja tegin uue listi, get ja put mapping jaoks

    @GetMapping("sample") // panen run ning lähen -> http://localhost:8080/sample
    public String helloName(@RequestParam("Hello") String action) { //Hello on muutuja action´is
        return action + " "; // http://localhost:8080/sample?Hello=Mari
    }

    @GetMapping("Fibonacci") // http://localhost:8080/Fibonacci?value=3
    public int Fibonacci(@RequestParam("value") int n) {
        return Lesson2.fibonacci(n);
    }

    @GetMapping("Fibonacci/{n}") // http://localhost:8080/Fibonacci/5
    public int Fibonacci2(@PathVariable("n") int n) {
        return Lesson2.fibonacci(n);
    }

    @GetMapping("reverseArray/{value}") // http://localhost:8080/reverseArray/10,9,8 ; v: [8,9,10]
    public int[] reverseArray(@PathVariable("value") int[] array) { // array on URL´i muutuja nimetus
        return Lesson2.reverseArray(array);
    }

    @GetMapping("evenNumbers/{value}")
    // http://localhost:8080/evenNumbers/6 (väljastab 6 paarisarvu) ; v: [2,4,6,8,10,12]
    public int[] evenNumbers(@PathVariable("value") int n) {
        return Lesson2.evenNumbers(n);
    }

    @GetMapping("min/{value}") // http://localhost:8080/min/1,2,3,4,5 ; v: 1
    public int min(@PathVariable("value") int[] x) {
        return Lesson2.min(x);
    }

    @GetMapping("max/{value}") // http://localhost:8080/max/1,2,3,4,5 ; v: 5
    public int max(@PathVariable("value") int[] x) {
        return Lesson2.max(x);
    }

    @GetMapping("sum") // http://localhost:8080/sum?value=1,2,3 ; v: 6
    public int sum(@RequestParam("value") int[] x) {
        return Lesson2.sum(x);
    }

    @GetMapping("sampleArray") // http://localhost:8080/sampleArray ; v: [1,2,3,4,5]
    public int[] sampleArray() { // muutuja puudub
        return Lesson2b.sampleArray();
    }

    @GetMapping("generateArray") // http://localhost:8080/generateArray?value=5 ; v: [1,2,3,4,5]
    public int[] generateArray(@RequestParam("value") int n) {
        return Lesson2b.generateArray(n);
    }

    @GetMapping("decreasingArray") // http://localhost:8080/decreasingArray?value=5 ; v: [5,4,3,2,1,0]
    public int[] decreasingArray(@RequestParam("value") int n) {
        return Lesson2b.decreasingArray(n);
    }

    @GetMapping("yl3") // http://localhost:8080/yl3?value=5 ; v: [3,3,3,3,3]
    public int[] yl3(@RequestParam("value") int n) {
        return Lesson2b.yl3(n);
    }

    @GetMapping("abs") // http://localhost:8080/abs?value=-5 ; v: 5
    public int abs(@RequestParam("value") int a) {
        return Lesson1.abs(a);
    }

    @GetMapping("isEven") // http://localhost:8080/isEven?a=10 ; v: true
    public boolean isEven(@RequestParam("a") int a) {
        return Lesson1.isEven(a);
    }

    @GetMapping("min3")
    public int min3(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) { //http://localhost:8080/min3?a=50&b=20&c=30 ; v: 20
        return Lesson1.min3(a, b, c);
    }

    @GetMapping("max3")
    public int max3(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) { // http://localhost:8080/max3?a=50&b=20&c=30; v: 50
        return Lesson1.max3(a, b, c);
    }

    @GetMapping("Student") // http://localhost:8080/Student; v: {"name":"Kati","age":"2","grade":"4,5"}
    public Student studentInfo() {
        Student student = new Student();
        student.setName("Kati");
        student.setAge("2");
        student.setGrade("4.5");
        return student;
    }

    @GetMapping("Employee") // http://localhost:8080/Employee; v: {"name":"Mari","aadress":"Tallinn"}
    public Employee funktsiooniNimi() {
        Employee employee = new Employee();
        employee.setName("Mari");
        employee.setAadress("Tallinn");
        return employee;
    }

    @PostMapping("Employee") // http://localhost:8080/Employee; v: {"name":"Mari","aadress":"Tallinn"}
    public Employee funktsiooniPost(@RequestBody Employee employee) {
        System.out.println(employee.getName());
        System.out.println(employee.getAadress());
        return employee;
    }

    // Employee List
    @GetMapping("EmployeeList")
    public List<Employee> employees() {
        return employeeList;
    }

    // Employee ID
    @GetMapping("EmployeeList/{id}") //{}pathVariable
    public Employee oneEmployee(@PathVariable("id") int id) {
        return employeeList.get(id);
    }

    // saan sisestada uue töötaja
    @PostMapping("EmployeeList")
    public void addEmployee(@RequestBody Employee employee) { // void, sest ei tagasta // RequestBody = 1
        employeeList.add(employee);
    }

    @PutMapping("EmployeeList/{id}")
    public void replaceEmployee(@PathVariable ("id") int id, @RequestBody Employee employee) {
        employeeList.set(id,employee);
    }

    @DeleteMapping("EmployeeList/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeList.remove(id);
    }
}
