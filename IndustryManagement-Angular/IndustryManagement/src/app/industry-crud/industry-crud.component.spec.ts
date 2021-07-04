import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndustryCrudComponent } from './industry-crud.component';

describe('IndustryCrudComponent', () => {
  let component: IndustryCrudComponent;
  let fixture: ComponentFixture<IndustryCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndustryCrudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndustryCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
