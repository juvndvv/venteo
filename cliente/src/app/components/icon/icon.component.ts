import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-icon',
  standalone: true,
  imports: [],
  templateUrl: './icon.component.html',
  styleUrl: './icon.component.css'
})
export class IconComponent {
  @Input({required: true}) type: string = '';
  @Input() color: string = '#fff';
  @Input() viewBox: string = '0 0 173 173';
  constructor() {}
}
