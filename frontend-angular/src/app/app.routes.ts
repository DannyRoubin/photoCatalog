import { Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { HomePageComponent } from './components/home-page/home-page.component';

export const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    providers: [provideHttpClient()],
  },
];
