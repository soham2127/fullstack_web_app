import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-employee-list',
  standalone: false,
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})

export class EmployeeListComponent implements OnInit{

  employees: Employee[]=[];
  constructor(private employeeService: EmployeeService,private router: Router){}

  ngOnInit(): void{
    this.getEmployees();
  }
//event handlers belows
  private getEmployees(){
    this.employeeService.getEmployeeList().subscribe(data=>{
      this.employees=data
    });
  }

  updateEmployee(id: number){

    this.router.navigate(['update-employee',id]);
  }

  deleteEmployee(id: number){

    this.employeeService.deleteEmployee(id).subscribe(data=>{
      console.log(data);
      this.getEmployees();
    })
  }
  employeeDetails(id: number){
    this.router.navigate(['employee-details',id]);
  }
  
}
