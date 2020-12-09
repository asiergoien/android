package com.example.e15_animacion;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView o_ImageView;
    ImageView o_VectorAnimado;
    TextView o_texto;
    ImageView o_BalonDeArriba;
    ImageView o_BalonDeAbajo;
    ImageView o_FormaAnimada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        o_BalonDeArriba = findViewById(R.id.BalonDearriba_imageView);
        o_BalonDeAbajo = findViewById(R.id.BalonDeAbajoimageView);
        o_FormaAnimada = findViewById(R.id.formaAnimada_imageView);
        o_VectorAnimado = findViewById(R.id.vector_animado);

        //Aqui se les pone para ponerles animaciones
        o_BalonDeArriba.setOnClickListener(this::mover_2_Elementos);
        o_BalonDeAbajo.setOnClickListener(this::mover_ValueAnimator);
        o_FormaAnimada.setOnClickListener(this::mover_formaAnimada);
        o_VectorAnimado.setOnClickListener(this::mover_vectorAnimado);

    }
    public void mover_2_Elementos(View v) {
        float fFin = 700;
        ObjectAnimator oArribaAnimator =
                ObjectAnimator.ofFloat(o_BalonDeArriba, "translationX", fFin);
        ObjectAnimator oAbajoAnimator =
                ObjectAnimator.ofFloat(o_BalonDeAbajo, "translationX", fFin);
        oArribaAnimator.setDuration(2000L);
        oAbajoAnimator.setDuration(2000L);
        oAbajoAnimator.setStartDelay(500L);
        AnimatorSet oAnimatorSet = new AnimatorSet();
        oAnimatorSet.playSequentially(oArribaAnimator,
                oAbajoAnimator);
        oAnimatorSet.start();
    }

    public void mover_Animation(View v) {
        Animation oAnimacion =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.mover_derecha);
        o_ImageView.startAnimation(oAnimacion);
    }
    public void mover_animacion(View v) {
        Animation animacion = AnimationUtils.loadAnimation(this,
                R.anim.animacion_compleja);
        o_texto.startAnimation(animacion);
    }
    public void mover_ValueAnimator(View v) {
        ConstraintLayout oConstraint = (ConstraintLayout) o_BalonDeAbajo.getParent();
        float fInicio = oConstraint.getLeft();
        float fFin = 600;
        ValueAnimator oValueAnimator = ValueAnimator.ofFloat(fInicio, fFin);
        oValueAnimator.setDuration(2000L);
        oValueAnimator.setStartDelay(500L);
        oValueAnimator.setInterpolator(new AccelerateInterpolator());
        oValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                o_BalonDeAbajo.setTranslationX((float)animation.getAnimatedValue());
            }
        });
        oValueAnimator.start();
    }
    public void mover_ObjectAnimator(View v) {
        float fFin = 700;
        ObjectAnimator oObjectAnimator = ObjectAnimator.ofFloat(o_ImageView, "translationX", fFin);
        oObjectAnimator.setDuration(2000L);
        oObjectAnimator.setStartDelay(500L);
        oObjectAnimator.setInterpolator(new AccelerateInterpolator());
        oObjectAnimator.start();
    }
    public void mover_ObjectAnimator_2(View v) {
        float fFin = 700;
        ObjectAnimator oObjectAnimator =
                ObjectAnimator.ofPropertyValuesHolder(
                        o_ImageView,
                        PropertyValuesHolder.ofFloat("translationX", fFin),
                        PropertyValuesHolder.ofFloat("scaleX", 2f),
                        PropertyValuesHolder.ofFloat("scaleY", 2f)
                );
        oObjectAnimator.setDuration(2000L);
        oObjectAnimator.setStartDelay(500L);
        oObjectAnimator.start();
    }
    public void mover_ViewProperty(View v) {
        ViewPropertyAnimator oAnimation = o_BalonDeArriba.animate();
        oAnimation.alpha(0f).scaleX(2f).scaleY(2f).rotation(100f);
        oAnimation.setDuration(2000);
        oAnimation.start();
    }
    public void mover_formaAnimada(View v) {
        ((AnimationDrawable) o_FormaAnimada.getDrawable()).start();
    }
    public void mover_vectorAnimado(View v) {
        ((AnimatedVectorDrawable) o_VectorAnimado.getDrawable()).start();
    }






}