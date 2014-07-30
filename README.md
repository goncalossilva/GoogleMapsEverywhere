# GoogleMapsEveywhere

GoogleMapsEverywhere facilitates the use of Google Maps on Android devices that don't have or don't support the Google Play Services.

Internally, it's a wrapper around the Google Maps JavaScript API v3.

## Usage

For the most part, the API is the same as Google Maps Android API v2, so the documentation for it applies.

The API key must be specified in your AndroidManifest.xml, like this:

    <meta-data
        android:name="com.google.android.maps.v3.API_KEY"
        android:value="YOUR_API_KEY" />

It should be a regular Google Maps JavaScript API v3.

Unlike Google Maps on Android, GoogleMapsEverywhere doesn't need any special permission to run besides the `android.permission.INTERNET` permission, which is required by its own `AndroidManifest.xml`. However, if you use `GoogleMap#setMyLocationEnabled(true)` you'll need to declare the `android.permission.ACCESS_FINE_LOCATION` permission in your `AndroidManifest.xml` file.

## License

    Copyright (c) 2014 Gonçalo Silva

    Permission is hereby granted, free of charge, to any person obtaining
    a copy of this software and associated documentation files (the
    "Software"), to deal in the Software without restriction, including
    without limitation the rights to use, copy, modify, merge, publish,
    distribute, sublicense, and/or sell copies of the Software, and to
    permit persons to whom the Software is furnished to do so, subject to
    the following conditions:

    The above copyright notice and this permission notice shall be
    included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
    MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
    NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
    LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
    OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
    WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.- 3. 
