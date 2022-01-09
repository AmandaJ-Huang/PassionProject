import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./components/home";
import { AboutComponent } from "./components/about";
import { FinderComponent } from "./components/finder";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'about', component: AboutComponent },
    { path: 'finder', component: FinderComponent },

    // redirects to home page
    { path: '**', redirectTo: '' }
];

export const AppRoutingModule = RouterModule.forRoot(routes);