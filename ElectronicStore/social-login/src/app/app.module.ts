import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { SocialLoginModule, SocialAuthServiceConfig } from '@abacritt/angularx-social-login';
import {
  GoogleLoginProvider,
  FacebookLoginProvider
} from '@abacritt/angularx-social-login';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import{HttpClientModule} from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SocialLoginModule,
    HttpClientModule
  ],
  providers: [
  {
                    provide: 'SocialAuthServiceConfig',
                    useValue: {
                      autoLogin: false,
                      providers: [
                        {
                          id: GoogleLoginProvider.PROVIDER_ID,
                          provider: new GoogleLoginProvider(
                            '324640218363-tg9v6jh05k2scvuh7tfmftti9hukbnrd.apps.googleusercontent.com'
                          )
                        },
                        {
                          id: FacebookLoginProvider.PROVIDER_ID,
                          provider: new FacebookLoginProvider('clientId')
                        }
                      ],
                      onError: (err) => {
                        console.error(err);
                      }
                    } as SocialAuthServiceConfig,
                  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }