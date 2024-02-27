import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryAuctionComponent } from './category-auction.component';

describe('CategoryAuctionComponent', () => {
  let component: CategoryAuctionComponent;
  let fixture: ComponentFixture<CategoryAuctionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryAuctionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CategoryAuctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
