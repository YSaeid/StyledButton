# StyledButton
A button With self style and no need to generate drawable for background, fill style, stroke style, round style and ....

<img src="https://github.com/YSaeid/StyledButton/blob/master/StyledButton.gif">
<h1>Installation</h1>
<span>Add this line to your project build.gradle</span>

```javascript
allprojects {
    repositories {
        maven { url 'https://jitpack.io' } //add this
        jcenter()
    }
}
```

<span>Add this line to your Module build.gradle</span>

```javascript
dependencies {
    compile 'com.github.YSaeid:StyledButton:1.0.0'
}
```

<span>Add The StyledButton to your layout xml file</span>

```javascript
    <yazdany.saeid.buttonstyledwithelevation.StyledButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

<span>use this attribute</span>

```javascript
        app:buttonStyleType="stroke"
        app:buttonElevation="4"
        app:buttonRadius="5"
        app:buttonTextColor="@color/colorPrimary"
        app:buttonTextSize="12"
        app:buttonText="Test"
        app:buttonElevationColor="@color/colorAccent"
        app:buttonColor="#a1887f"
        app:buttonTextGravity="center"
        app:buttonStrokeWidth="6dp"
        app:buttonStrokeColor="@color/colorAccent"
 ```
 
 ```javascript
    <yazdany.saeid.buttonstyledwithelevation.StyledButton
        app:buttonStyleType="stroke"
        app:buttonElevation="4"
        app:buttonRadius="5"
        app:buttonTextColor="@color/colorPrimary"
        app:buttonTextSize="12"
        app:buttonText="Test"
        app:buttonElevationColor="@color/colorAccent"
        app:buttonColor="#a1887f"
        app:buttonTextGravity="center"
        app:buttonStrokeWidth="6dp"
        app:buttonStrokeColor="@color/colorAccent"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true"/>
```

<h3>Ripple is not working, but in the later version i impelement it. have fun with this library.</h3>
