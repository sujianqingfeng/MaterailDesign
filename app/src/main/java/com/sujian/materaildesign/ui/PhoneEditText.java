package com.sujian.materaildesign.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

public class PhoneEditText extends ClearEditText {

    private static final int ID_SELECTION_MODE = android.R.id.selectTextMode;
    // Selection context mode
    private static final int ID_SELECT_ALL = android.R.id.selectAll;
    private static final int ID_CUT = android.R.id.cut;
    private static final int ID_COPY = android.R.id.copy;
    private static final int ID_PASTE = android.R.id.paste;
    private static final int ID_PASTE_AS_PLAIN_TEXT = android.R.id.pasteAsPlainText;


    public PhoneEditText(Context context) {
        super(context);
        init();
    }

    public PhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PhoneEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private InputFilter[] mInputFilters_Phone = new InputFilter[]{
            new InputFilter() {

                @Override
                public CharSequence filter(CharSequence source, int start,
                                           int end, Spanned dest, int dstart, int dend) {
                    // return source.toString().replaceAll("[^\\d]", "");
                    String s = source.toString().replaceAll("[^\\d]", "");
                    if (s.length() > 3) {
                        s = s.substring(0, 3) + " "
                                + s.substring(3, s.length());
                    }
                    if (s.length() > 8) {
                        s = s.substring(0, 8) + " "
                                + s.substring(8, s.length());
                    }
                    return s;
                }

            }, new InputFilter.LengthFilter(13)};

    private void init() {
        setInputType(InputType.TYPE_CLASS_PHONE);
        setFilters(mInputFilters_Phone);
        addTextChangedListener(new MyTextWatcher());
        // setOnFocusChangeListener(this);
    }

    /**
     * use {@link #getPhone()} to replace
     *
     * @return
     */
    @Deprecated
    public Editable getText() {
        return super.getText();
    }

    /**
     * 获取手机号码
     *
     * @return
     */
    public String getPhone() {
        return getText().toString().replaceAll("[^\\d]", "");
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        Log.d("pengjian", "id:" + id);
        if (id == ID_PASTE || id == ID_PASTE_AS_PLAIN_TEXT) {
            // 粘贴
            PhoneEditText.this.setSelection(0);
            Toast.makeText(getContext(), "paste:" + getText(), Toast.LENGTH_SHORT).show();
        }
        return super.onTextContextMenuItem(id);
    }

    class MyTextWatcher implements TextWatcher {

        int beforeLen = 0;
        int afterLen = 0;
        int start = 0;
        int after = 0;
        int before = 0;

        boolean flag = true;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            if (flag) {
                //Log.e("beforeTextChanged", "start1="+start);
                String txt = s.toString().replaceAll("[^\\d]", "");
                beforeLen = txt.length();
                this.start = start;
                this.after = after;
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (flag) {
                this.before = before;
            }
        }

        @Override
        public void afterTextChanged(Editable source) {
            // 启用保护标志，防止递归死循环
            if (flag) {
                flag = false;
                // 去除所有空格
                String txt = source.toString().replaceAll("[^\\d]", "");
                afterLen = txt.length();
                // 重新格式化手机号码
                StringBuffer sb = new StringBuffer(txt);
                if (afterLen > 3) {
                    sb.insert(3, " ");

                }
                if (afterLen > 7) {
                    sb.insert(8, " ");
                }
                // 显示字符
                PhoneEditText.this.setText(sb.toString());
                // 判断输入或删除行为，控制光标位置
                if (afterLen > beforeLen) {
                    // 输入
                    if (start == 3 || start == 8)
                        start += 1;
                    PhoneEditText.this.setSelection(Math.min(sb.length(), start
                            + after));
                } else {
                    // 删除
                    PhoneEditText.this
                            .setSelection(Math.min(sb.length(), start));
                }
                // 执行结束，恢复标志位
                flag = true;
            }
        }

    }

}
