import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {industry} from "./industry";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IndustryServiceService {
  constructor(private http:HttpClient) { }

  getIndustries(): Observable<industry[]>{
    return this.http.get<industry[]>("http://localhost:8080/Assignment30359953-war/webResource/greeting/getallindustry");
  }

}
