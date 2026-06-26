import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentPortal } from './agent-portal';

describe('AgentPortal', () => {
  let component: AgentPortal;
  let fixture: ComponentFixture<AgentPortal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgentPortal],
    }).compileComponents();

    fixture = TestBed.createComponent(AgentPortal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
