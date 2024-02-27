import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateAuctionComponent } from './generate-auction.component';

describe('GenerateAuctionComponent', () => {
  let component: GenerateAuctionComponent;
  let fixture: ComponentFixture<GenerateAuctionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenerateAuctionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GenerateAuctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
