import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfDtataComponent } from './list-of-dtata.component';

describe('ListOfDtataComponent', () => {
  let component: ListOfDtataComponent;
  let fixture: ComponentFixture<ListOfDtataComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListOfDtataComponent]
    });
    fixture = TestBed.createComponent(ListOfDtataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
