package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    List<String> list=new ArrayList<>();
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler=findViewById(R.id.recycler);
        for (int i = 0; i < 20; i++) {
            list.add(i+"");
        }
        adapter=new MyAdapter(R.layout.item,list,this);
        recycler.setLayoutManager(new GridLayoutManager(this,3));
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new GridViewDecoration(this,R.drawable.item_driver));
//        startActivity();
    }


    class RecyclerItemDecoration extends RecyclerView.ItemDecoration{
        private Paint paint;
        public RecyclerItemDecoration(){
            paint=new Paint();
            paint.setColor(Color.BLUE);
            paint.setAntiAlias(true);
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position=parent.getChildAdapterPosition(view);
            Log.e("gao", "getItemOffsets: "+position);
//            if(position!=0){
                outRect.bottom=10;
                outRect.left=20;
//            }
//            if(parent.getChildAdapterPosition(view)!=parent.getChildCount()-1){

//            }
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            int childCount=parent.getChildCount();
            Rect rect=new Rect();
            Rect rect1=new Rect();
            rect.left=parent.getPaddingLeft();
            rect.right=parent.getWidth()-parent.getPaddingRight();
            rect1.left=0;
            rect1.right=50;

            for (int i = 0; i < childCount - 1; i++) {
                rect.bottom=parent.getChildAt(i).getTop();
                rect.top=parent.getBottom()-10;
                rect1.top=parent.getChildAt(i).getTop();
                Log.e("gao", "onDraw: "+rect1.top);
                rect1.bottom=parent.getChildAt(i).getBottom();
                Log.e("gao", "onDraw: "+rect1.bottom);
                c.drawRect(rect,paint);
                c.drawRect(rect1,paint);
            }


        }
    }


}
