import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-agent-portal',
  imports: [CommonModule],
  templateUrl: './agent-portal.html',
  styleUrls: ['./agent-portal.css']
})
export class AgentPortal implements OnInit {
  activeTab = 'roster';
  clients: any[] = [];
  
  // Mock data for Commission & Performance
  metrics = {
    totalBookings: 24,
    monthlyEarnings: 15400,
    activeQuotes: 5,
    pendingPayments: 2
  };

  // Mock data for Quoting Tool
  recentQuotes = [
    { id: 'Q-7829', client: 'John Doe', route: 'LAX -> JFK', amount: 35000, status: 'Sent' },
    { id: 'Q-7830', client: 'Jane Smith', route: 'LHR -> DXB', amount: 82000, status: 'Draft' },
    { id: 'Q-7831', client: 'Acme Corp', route: 'MIA -> TEB', amount: 28000, status: 'Accepted' }
  ];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchClients();
  }

  fetchClients() {
    this.http.get<any[]>('http://localhost:8080/api/v1/agent/clients').subscribe({
      next: (data) => {
        this.clients = data;
      },
      error: (err) => {
        console.error('Failed to fetch clients', err);
      }
    });
  }

  setTab(tab: string) {
    this.activeTab = tab;
  }
}
