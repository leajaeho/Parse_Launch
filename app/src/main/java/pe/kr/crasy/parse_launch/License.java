package pe.kr.crasy.parse_launch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Objects;

public class License extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        String ApacheLicense = "Apache License\n" +
                "\n" +
                "Version 2.0, January 2004\n" +
                "\n" +
                "http://www.apache.org/licenses/\n" +
                "\n" +
                "TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n" +
                "\n" +
                "1. Definitions.\n" +
                "\n" +
                "\"License\" shall mean the terms and conditions for use, reproduction, and distribution as defined by Sections 1 through 9 of this document.\n" +
                "\n" +
                "\"Licensor\" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.\n" +
                "\n" +
                "\"Legal Entity\" shall mean the union of the acting entity and all other entities that control, are controlled by, or are under common control with that entity. For the purposes of this definition, \"control\" means (i) the power, direct or indirect, to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.\n" +
                "\n" +
                "\"You\" (or \"Your\") shall mean an individual or Legal Entity exercising permissions granted by this License.\n" +
                "\n" +
                "\"Source\" form shall mean the preferred form for making modifications, including but not limited to software source code, documentation source, and configuration files.\n" +
                "\n" +
                "\"Object\" form shall mean any form resulting from mechanical transformation or translation of a Source form, including but not limited to compiled object code, generated documentation, and conversions to other media types.\n" +
                "\n" +
                "\"Work\" shall mean the work of authorship, whether in Source or Object form, made available under the License, as indicated by a copyright notice that is included in or attached to the work (an example is provided in the Appendix below).\n" +
                "\n" +
                "\"Derivative Works\" shall mean any work, whether in Source or Object form, that is based on (or derived from) the Work and for which the editorial revisions, annotations, elaborations, or other modifications represent, as a whole, an original work of authorship. For the purposes of this License, Derivative Works shall not include works that remain separable from, or merely link (or bind by name) to the interfaces of, the Work and Derivative Works thereof.\n" +
                "\n" +
                "\"Contribution\" shall mean any work of authorship, including the original version of the Work and any modifications or additions to that Work or Derivative Works thereof, that is intentionally submitted to Licensor for inclusion in the Work by the copyright owner or by an individual or Legal Entity authorized to submit on behalf of the copyright owner. For the purposes of this definition, \"submitted\" means any form of electronic, verbal, or written communication sent to the Licensor or its representatives, including but not limited to communication on electronic mailing lists, source code control systems, and issue tracking systems that are managed by, or on behalf of, the Licensor for the purpose of discussing and improving the Work, but excluding communication that is conspicuously marked or otherwise designated in writing by the copyright owner as \"Not a Contribution.\"\n" +
                "\n" +
                "\"Contributor\" shall mean Licensor and any individual or Legal Entity on behalf of whom a Contribution has been received by Licensor and subsequently incorporated within the Work.\n" +
                "\n" +
                "2. Grant of Copyright License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable copyright license to reproduce, prepare Derivative Works of, publicly display, publicly perform, sublicense, and distribute the Work and such Derivative Works in Source or Object form.\n" +
                "\n" +
                "3. Grant of Patent License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable (except as stated in this section) patent license to make, have made, use, offer to sell, sell, import, and otherwise transfer the Work, where such license applies only to those patent claims licensable by such Contributor that are necessarily infringed by their Contribution(s) alone or by combination of their Contribution(s) with the Work to which such Contribution(s) was submitted. If You institute patent litigation against any entity (including a cross-claim or counterclaim in a lawsuit) alleging that the Work or a Contribution incorporated within the Work constitutes direct or contributory patent infringement, then any patent licenses granted to You under this License for that Work shall terminate as of the date such litigation is filed.\n" +
                "\n" +
                "4. Redistribution. You may reproduce and distribute copies of the Work or Derivative Works thereof in any medium, with or without modifications, and in Source or Object form, provided that You meet the following conditions:\n" +
                "\n" +
                "You must give any other recipients of the Work or Derivative Works a copy of this License; and\n" +
                "You must cause any modified files to carry prominent notices stating that You changed the files; and\n" +
                "You must retain, in the Source form of any Derivative Works that You distribute, all copyright, patent, trademark, and attribution notices from the Source form of the Work, excluding those notices that do not pertain to any part of the Derivative Works; and\n" +
                "If the Work includes a \"NOTICE\" text file as part of its distribution, then any Derivative Works that You distribute must include a readable copy of the attribution notices contained within such NOTICE file, excluding those notices that do not pertain to any part of the Derivative Works, in at least one of the following places: within a NOTICE text file distributed as part of the Derivative Works; within the Source form or documentation, if provided along with the Derivative Works; or, within a display generated by the Derivative Works, if and wherever such third-party notices normally appear. The contents of the NOTICE file are for informational purposes only and do not modify the License. You may add Your own attribution notices within Derivative Works that You distribute, alongside or as an addendum to the NOTICE text from the Work, provided that such additional attribution notices cannot be construed as modifying the License. \n" +
                "\n" +
                "You may add Your own copyright statement to Your modifications and may provide additional or different license terms and conditions for use, reproduction, or distribution of Your modifications, or for any such Derivative Works as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.\n" +
                "5. Submission of Contributions. Unless You explicitly state otherwise, any Contribution intentionally submitted for inclusion in the Work by You to the Licensor shall be under the terms and conditions of this License, without any additional terms or conditions. Notwithstanding the above, nothing herein shall supersede or modify the terms of any separate license agreement you may have executed with Licensor regarding such Contributions.\n" +
                "\n" +
                "6. Trademarks. This License does not grant permission to use the trade names, trademarks, service marks, or product names of the Licensor, except as required for reasonable and customary use in describing the origin of the Work and reproducing the content of the NOTICE file.\n" +
                "\n" +
                "7. Disclaimer of Warranty. Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor provides its Contributions) on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the Work and assume any risks associated with Your exercise of permissions under this License.\n" +
                "\n" +
                "8. Limitation of Liability. In no event and under no legal theory, whether in tort (including negligence), contract, or otherwise, unless required by applicable law (such as deliberate and grossly negligent acts) or agreed to in writing, shall any Contributor be liable to You for damages, including any direct, indirect, special, incidental, or consequential damages of any character arising as a result of this License or out of the use or inability to use the Work (including but not limited to damages for loss of goodwill, work stoppage, computer failure or malfunction, or any and all other commercial damages or losses), even if such Contributor has been advised of the possibility of such damages.\n" +
                "\n" +
                "9. Accepting Warranty or Additional Liability. While redistributing the Work or Derivative Works thereof, You may choose to offer, and charge a fee for, acceptance of support, warranty, indemnity, or other liability obligations and/or rights consistent with this License. However, in accepting such obligations, You may act only on Your own behalf and on Your sole responsibility, not on behalf of any other Contributor, and only if You agree to indemnify, defend, and hold each Contributor harmless for any liability incurred by, or claims asserted against, such Contributor by reason of your accepting any such warranty or additional liability.\n" +
                "\n" +
                "END OF TERMS AND CONDITIONS";
        String JSoupLicense = "jsoup License\n" +
                "The jsoup code-base (include source and compiled packages) are distributed under the open source MIT license as described below.\n" +
                "\n" +
                "The MIT License\n" +
                "Copyright © 2009 - 2013 Jonathan Hedley (jonathan@hedley.net)\n" +
                "\n" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:\n" +
                "\n" +
                "The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.\n" +
                "\n" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.";
        String ThisAppLicense = "Copyright [2015] [이재호]\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.";
        String materialdesigniconsLicense = "Copyright (c) 2014, Austin Andrews (http://materialdesignicons.com/),\n" +
                "with Reserved Font Name Material Design Icons.\n" +
                "Copyright (c) 2014, Google (http://www.google.com/design/)\n" +
                "uses the license at https://github.com/google/material-design-icons/blob/master/LICENSE\n" +
                "\n" +
                "This Font Software is licensed under the SIL Open Font License, Version 1.1.\n" +
                "This license is copied below, and is also available with a FAQ at:\n" +
                "http://scripts.sil.org/OFL\n" +
                "\n" +
                "\n" +
                "-----------------------------------------------------------\n" +
                "SIL OPEN FONT LICENSE Version 1.1 - 26 February 2007\n" +
                "-----------------------------------------------------------\n" +
                "\n" +
                "PREAMBLE\n" +
                "The goals of the Open Font License (OFL) are to stimulate worldwide\n" +
                "development of collaborative font projects, to support the font creation\n" +
                "efforts of academic and linguistic communities, and to provide a free and\n" +
                "open framework in which fonts may be shared and improved in partnership\n" +
                "with others.\n" +
                "\n" +
                "The OFL allows the licensed fonts to be used, studied, modified and\n" +
                "redistributed freely as long as they are not sold by themselves. The\n" +
                "fonts, including any derivative works, can be bundled, embedded, \n" +
                "redistributed and/or sold with any software provided that any reserved\n" +
                "names are not used by derivative works. The fonts and derivatives,\n" +
                "however, cannot be released under any other type of license. The\n" +
                "requirement for fonts to remain under this license does not apply\n" +
                "to any document created using the fonts or their derivatives.\n" +
                "\n" +
                "DEFINITIONS\n" +
                "\"Font Software\" refers to the set of files released by the Copyright\n" +
                "Holder(s) under this license and clearly marked as such. This may\n" +
                "include source files, build scripts and documentation.\n" +
                "\n" +
                "\"Reserved Font Name\" refers to any names specified as such after the\n" +
                "copyright statement(s).\n" +
                "\n" +
                "\"Original Version\" refers to the collection of Font Software components as\n" +
                "distributed by the Copyright Holder(s).\n" +
                "\n" +
                "\"Modified Version\" refers to any derivative made by adding to, deleting,\n" +
                "or substituting -- in part or in whole -- any of the components of the\n" +
                "Original Version, by changing formats or by porting the Font Software to a\n" +
                "new environment.\n" +
                "\n" +
                "\"Author\" refers to any designer, engineer, programmer, technical\n" +
                "writer or other person who contributed to the Font Software.\n" +
                "\n" +
                "PERMISSION & CONDITIONS\n" +
                "Permission is hereby granted, free of charge, to any person obtaining\n" +
                "a copy of the Font Software, to use, study, copy, merge, embed, modify,\n" +
                "redistribute, and sell modified and unmodified copies of the Font\n" +
                "Software, subject to the following conditions:\n" +
                "\n" +
                "1) Neither the Font Software nor any of its individual components,\n" +
                "in Original or Modified Versions, may be sold by itself.\n" +
                "\n" +
                "2) Original or Modified Versions of the Font Software may be bundled,\n" +
                "redistributed and/or sold with any software, provided that each copy\n" +
                "contains the above copyright notice and this license. These can be\n" +
                "included either as stand-alone text files, human-readable headers or\n" +
                "in the appropriate machine-readable metadata fields within text or\n" +
                "binary files as long as those fields can be easily viewed by the user.\n" +
                "\n" +
                "3) No Modified Version of the Font Software may use the Reserved Font\n" +
                "Name(s) unless explicit written permission is granted by the corresponding\n" +
                "Copyright Holder. This restriction only applies to the primary font name as\n" +
                "presented to the users.\n" +
                "\n" +
                "4) The name(s) of the Copyright Holder(s) or the Author(s) of the Font\n" +
                "Software shall not be used to promote, endorse or advertise any\n" +
                "Modified Version, except to acknowledge the contribution(s) of the\n" +
                "Copyright Holder(s) and the Author(s) or with their explicit written\n" +
                "permission.\n" +
                "\n" +
                "5) The Font Software, modified or unmodified, in part or in whole,\n" +
                "must be distributed entirely under this license, and must not be\n" +
                "distributed under any other license. The requirement for fonts to\n" +
                "remain under this license does not apply to any document created\n" +
                "using the Font Software.\n" +
                "\n" +
                "TERMINATION\n" +
                "This license becomes null and void if any of the above conditions are\n" +
                "not met.\n" +
                "\n" +
                "DISCLAIMER\n" +
                "THE FONT SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,\n" +
                "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO ANY WARRANTIES OF\n" +
                "MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT\n" +
                "OF COPYRIGHT, PATENT, TRADEMARK, OR OTHER RIGHT. IN NO EVENT SHALL THE\n" +
                "COPYRIGHT HOLDER BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,\n" +
                "INCLUDING ANY GENERAL, SPECIAL, INDIRECT, INCIDENTAL, OR CONSEQUENTIAL\n" +
                "DAMAGES, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING\n" +
                "FROM, OUT OF THE USE OR INABILITY TO USE THE FONT SOFTWARE OR FROM\n" +
                "OTHER DEALINGS IN THE FONT SOFTWARE.";
        TextView License = (TextView)findViewById(R.id.RealmLicense);
        Intent intent = getIntent();
        if(intent.getStringExtra("License").equals("com.getbase:floatingactionbutton")){
            setTitle("com.getbase:floatingactionbutton License");
            License.setText(ApacheLicense);
        }
        if(intent.getStringExtra("License").equals("ThisApp")){
            setTitle("ThisApp License");
            License.setText(ThisAppLicense);
        }
        if(intent.getStringExtra("License").equals("JSoup")){
            setTitle("JSoup License");
            License.setText(JSoupLicense);
        }
        if(intent.getStringExtra("License").equals("Realm")){
            setTitle("Realm License");
            License.setText(ApacheLicense);
        }
        if(intent.getStringExtra("License").equals("materialdesignicons")){
            setTitle("materialdesignicons License");
            License.setText(materialdesigniconsLicense);
        }
    }
}
