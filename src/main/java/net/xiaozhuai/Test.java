package net.xiaozhuai;

import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        try {
            getVerifyMailUrl(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getVerifyMailUrl(String t) throws IOException {
        FileReader file = new FileReader("F:\\processon\\signup.txt");
        BufferedReader reader = new BufferedReader(file);
        int len = 0;
        String str = null;
        List<String> list = new ArrayList<>();
        while ((str=reader.readLine())!=null){
            list.add(str);
        }
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static String text = "<html>\n" +
            " <head></head>\n" +
            " <body>\n" +
            "  <div class=\"inboxWarpMain\"> \n" +
            "   <div class=\"inbox-area\"> \n" +
            "    <div class=\"inbox-header hidden-xs-sm\"> \n" +
            "     <div class=\"col-h\">\n" +
            "      发送人\n" +
            "     </div> \n" +
            "     <div class=\"col-h\">\n" +
            "      主题：\n" +
            "     </div> \n" +
            "     <div class=\"col-h small-h\">\n" +
            "      浏览\n" +
            "     </div> \n" +
            "    </div> \n" +
            "    <div class=\"bg-header d-none visable-xs-sm\"> \n" +
            "     <span class=\"spanH1\">收件箱</span> \n" +
            "    </div> \n" +
            "    <div class=\"inbox-dataList\"> \n" +
            "     <ul> \n" +
            "      <li> \n" +
            "       <div class=\"col-box\"> \n" +
            "        <a href=\"https://temp-mail.org/zh/view/1f0dc3ba8d713d575eac1f9be735ca00\" title=\"激活您的ProcessOn帐号\"> <span class=\"inboxSenderName\"><span class=\"bullets-ico is-active\"></span>ProcessOn</span> <span class=\"inboxSenderEmail\"></span> <span class=\"inboxSubject subject-title d-none visable-xs-sm\"><small>主题：</small> 激活您的ProcessOn帐号</span> </a> \n" +
            "       </div> \n" +
            "       <div class=\"col-box hidden-xs-sm\"> \n" +
            "        <span class=\"inboxSubject subject-title\"> <a href=\"https://temp-mail.org/zh/view/1f0dc3ba8d713d575eac1f9be735ca00\" title=\"激活您的ProcessOn帐号\" class=\"title-subject\">激活您的ProcessOn帐号</a> </span> \n" +
            "       </div> \n" +
            "       <div class=\"col-box\"> \n" +
            "        <div class=\"attachment\"> \n" +
            "        </div> \n" +
            "        <div class=\"m-link-view\"> \n" +
            "         <a href=\"https://temp-mail.org/zh/view/1f0dc3ba8d713d575eac1f9be735ca00\" class=\"link\"> \n" +
            "          <svg class=\"arrow-link-ico\" width=\"10\" height=\"24\" viewbox=\"0 0 10 24\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\"> \n" +
            "           <path d=\"M3.33301 16.25L7.49967 11.25L3.33301 6.25\" stroke=\"#CDCDD8\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path> \n" +
            "          </svg> </a> \n" +
            "        </div> \n" +
            "       </div> </li> \n" +
            "     </ul> \n" +
            "    </div> \n" +
            "   </div> \n" +
            "  </div> \n" +
            "  <!-- Inbox SEO block --> \n" +
            "  <div> \n" +
            "   <div class=\"ads-box ad-block-728X90 ad-block-728X90-before-seo-text\"> \n" +
            "    <!--            <img src=\"http://temp-mail.loc/images/ad728x90.png\" alt=\"\">--> \n" +
            "    <!-- Ezoic - Before SEO text block banner - mid_content --> \n" +
            "    <div id=\"ezoic-pub-ad-placeholder-141\"></div> \n" +
            "    <!-- End Ezoic - Before SEO text block banner - mid_content --> \n" +
            "   </div> \n" +
            "  </div> \n" +
            "  <div class=\"mid-intro-text\"> \n" +
            "   <h1>什么是一次性临时电子邮件？</h1> \n" +
            "   <p><b>一次性电子邮件</b> - 是一项服务，允许在经过一段时间后自毁的临时地址接收电子邮件。 它也被称为：tempmail，10minutemail，一次性电子邮件，假邮件或垃圾邮件。 许多论坛，Wi-Fi所有者，网站和博客要求访问者注册，然后才能查看内容，发表评论或下载内容。 Temp-mail - 是最先进的免费电子邮件服务，可帮助您避免垃圾邮件并保持安全。</p> \n" +
            "  </div> \n" +
            "  <!-- End Inbox SEO block --> \n" +
            "  <!--Begin comScore Tag-->\n" +
            "  <script>var _comscore=_comscore||[];_comscore.push({c1:\"2\",c2:\"20015427\"});(function(){var s=document.createElement(\"script\"),el=document.getElementsByTagName(\"script\")[0];s.async=true;s.src=(document.location.protocol==\"https:\"?\"https://sb\":\"http://b\")+\".scorecardresearch.com/beacon.js\";el.parentNode.insertBefore(s,el)})();</script>\n" +
            "  <noscript>\n" +
            "   <img src=\"https://sb.scorecardresearch.com/p?c1=2&amp;c2=20015427&amp;cv=2.0&amp;cj=1\">\n" +
            "  </noscript>\n" +
            "  <!--End comScore Tag--> \n" +
            "  <!--Quantcast Tag-->\n" +
            "  <script type=\"text/javascript\">var _qevents=_qevents||[];(function(){var elem=document.createElement('script');elem.src=(document.location.protocol==\"https:\"?\"https://secure\":\"http://edge\")+\".quantserve.com/quant.js\";elem.async=true;elem.type=\"text/javascript\";var scpt=document.getElementsByTagName('script')[0];scpt.parentNode.insertBefore(elem,scpt)})();_qevents.push({qacct:\"p-31iz6hfFutd16\",labels:\"Domain.temp_mail_org,DomainId.30309\"});</script>\n" +
            "  <noscript>\n" +
            "   <div style=\"display:none;\">\n" +
            "    <img src=\"//pixel.quantserve.com/pixel/p-31iz6hfFutd16.gif?labels=Domain.temp_mail_org,DomainId.30309\" border=\"0\" height=\"1\" width=\"1\" alt=\"Quantcast\">\n" +
            "   </div>\n" +
            "  </noscript>\n" +
            "  <!--End Quantcast tag--> \n" +
            "  <script type=\"text/javascript\" style=\"display:none;\" async>\n" +
            "__ez.queue.addFile('edmonton.php', '/detroitchicago/edmonton.webp?a=a&cb=1&shcb=31', true, [], true, false, false, false);\n" +
            "__ez.queue.addFile('jellyfish.php', '/porpoiseant/jellyfish.webp?a=a&cb=1&shcb=31', false, [], true, false, false, false);\n" +
            "</script> \n" +
            " </body>\n" +
            "</html>";
}
