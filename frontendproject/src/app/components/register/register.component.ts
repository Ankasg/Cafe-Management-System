import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
user=new User();
constructor(private userService:UserService,private router: Router){}
registerUser()
{
this.userService.registerUser(this.user).subscribe(
  (response:any)=>{
    console.log(response);
    alert("registered successfully");
    this.router.navigate(['']);
  }
)
}

}
