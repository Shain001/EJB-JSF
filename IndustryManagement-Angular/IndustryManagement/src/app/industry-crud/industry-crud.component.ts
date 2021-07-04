import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {IndustryServiceService} from "../industry-service.service";

@Component({
  selector: 'app-industry-crud',
  templateUrl: './industry-crud.component.html',
  styleUrls: ['./industry-crud.component.scss']
})
export class IndustryCrudComponent implements OnInit {
  industryFormGroup: FormGroup;
  public industries = []
  constructor(private _industryService: IndustryServiceService) {
  }

  ngOnInit(): void {
    this.industryFormGroup = new FormGroup(
      {
        name : new FormControl("")
      },
    );
    this._industryService.getIndustries().subscribe(data => this.industries = data)
  }


  clearForm() {
    this.industryFormGroup.reset({});
  }
}
