package net.mobctrl.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.mobctrl.ItemBean;
import net.mobctrl.treerecyclerview.R;
import net.mobctrl.views.CircleImageView;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class ChildViewHolder extends BaseViewHolder {

    private TextView tv_time;
    private TextView tv_address;
    private LinearLayout linearLayout;
    private TextView tv_content;
    private CircleImageView headView;
    public TextView tv_title_name;
    public ImageView background;

    public ChildViewHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.container);
        tv_title_name = (TextView) itemView.findViewById(R.id.tv_title_name);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        tv_address = (TextView) itemView.findViewById(R.id.tv_address);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        background = (ImageView) itemView.findViewById(R.id.iv_background);
        headView = (CircleImageView) itemView.findViewById(R.id.civ_head_view);
    }

    public void bindView(ItemBean bean, int position) {
        tv_title_name.setText(bean.name);
        ViewGroup.LayoutParams params = background.getLayoutParams();
        params.height = bean.height;
        background.setLayoutParams(params);
    }

}
