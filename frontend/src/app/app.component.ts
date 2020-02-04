import { Component, OnInit } from '@angular/core';
import { UserService } from './+service/user.service';
import { User } from './+service/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private userService: UserService) {}

  user: User;

  ngOnInit(): void {
    this.user = this.userService.user;
  }
}
