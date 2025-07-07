import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: false,
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = new User();

  constructor(private userService: UserService, private router: Router) {}

  loginUser() {
    this.userService.loginUser(this.user).subscribe(
      (response: any) => {
        if (response != null) {
          sessionStorage.setItem('userId', response.userId);
          if (response.role === 'admin') {
            console.log(response);
            alert("Login successfully");
            this.router.navigate(['/adminhome']);
          } else if (response.role === 'user') {
            console.log(response);
            alert("Login successfully");
            this.router.navigate(['/userhome']);
          }
        } else {
          // ✅ If backend sends 200 with null (optional)
          alert("Invalid email or password.");
        }
      },
      (error) => {
        // ✅ Required for handling 401 or other backend errors
        if (error.status === 401) {
          alert("Invalid email or password.");
        } else {
          alert("Something went wrong. Please try again later.");
        }
      }
    );
  }
}
