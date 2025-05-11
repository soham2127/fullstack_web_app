import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  standalone: false,
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit {

  // employee = {
  //   firstName: '',
  //   lastName: '',
  //   emailId: ''
  // };
  id!: number;

  employee: Employee=new Employee();

  constructor(private employeeService: EmployeeService,private route: ActivatedRoute,private router: Router) {}

  ngOnInit(): void {
    // Optionally fetch employee data here
    this.id=this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee=data;
    }, error => console.log(error));
  }

  onSubmit() {
    console.log('Employee data submitted:', this.employee);
    // Add logic to update employee
    this.employeeService.updateEmployee(this.id,this.employee).subscribe(data=>{
      this.gotoEmployeeList();
    }, error=>console.log(error));
  }

  gotoEmployeeList(){
    this.router.navigate(['/employees']);
  }
}
