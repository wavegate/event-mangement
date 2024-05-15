import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.userService.register(this.registerForm.value).subscribe(
        (response) => {
          console.log('User registered successfully:', response);
          this.userService
            .login({
              username: this.registerForm.value.username,
              password: this.registerForm.value.password,
            })
            .subscribe(
              (loginResponse) => {
                this.userService.storeToken(loginResponse.jwt);
                this.router.navigate(['/']); // Redirect to home page or another page
              },
              (loginError) => {
                console.error(
                  'Error logging in user after registration:',
                  loginError
                );
              }
            );
        },
        (error) => {
          console.error('Error registering user:', error);
        }
      );
    }
  }
}
