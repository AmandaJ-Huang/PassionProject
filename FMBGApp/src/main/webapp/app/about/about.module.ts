import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";

import { SharedModule } from "app/shared/shared.module";
import { ABOUT_ROUTE } from "./about.route";
import { AboutComponent } from "./about.component";

@NgModule({
    imports: [SharedModule, RouterModule.forChild([ABOUT_ROUTE])],
    declarations: [AboutComponent],
})
export class AboutModule {}