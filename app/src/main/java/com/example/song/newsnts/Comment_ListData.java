package com.example.song.newsnts;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by 서영 on 2016-09-19.
 */
public class Comment_ListData {
    //리스트 정보를 담고 있을 객체 생성
    //이미지
    public Drawable comment_Img1;
    //프로필 아이디
    public String comment_Txt1;
    //내용
    public String comment_Txt2;

    //알파벳 이름으로 정렬
    public static final Comparator<Comment_ListData> ALPHA_COMPARATOR = new Comparator<Comment_ListData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(Comment_ListData mListData_1, Comment_ListData mListData_2) {
            return sCollator.compare(mListData_1.comment_Txt1, mListData_2.comment_Txt2);
        }
    };
}
