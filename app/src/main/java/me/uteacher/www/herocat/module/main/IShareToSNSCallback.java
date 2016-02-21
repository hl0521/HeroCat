package me.uteacher.www.herocat.module.main;

import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/2/19.
 */
public interface IShareToSNSCallback {

    public void onShareToWechatClicked(IInstagramModel instagramModel);

    public void onShareToWechatTimelineClicked(IInstagramModel instagramModel);

    public void onShareToQQClicked(IInstagramModel instagramModel);

    public void onShareToWeiboClicked(IInstagramModel instagramModel);

}
