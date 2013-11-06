<?php  session_start();
include "modal/modal-addpersonnel/keypersonnel.data.php";

$role = $_SESSION['keyPersonnel'][$id]['personnel_role'];
$piSelected = '';
$copiSelected = '';
if($role == "pi") $piSelected = 'selected="selected"';
if($role == "copi") $copiSelected = 'selected="selected"';

?>
<div class="panel panel-default" style="margin-bottom: 6px;" id="keyPersonnelEntry<?php echo $id?>">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse<?php echo $id?>"> <span aria-hidden="true" class="icon-caret-down"></span> <?php echo $persons[$id]['name']?> </a>&nbsp;<span class="text-muted"><?php echo $group[$_SESSION['keyPersonnel'][$id]['personnel_role']]?></span></h4>
            </div>
            <div class="col-md-6">
                <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove remove-person" entryId="<?php echo $id?>"></span></a>
            </div>
        </div>
    </div>
    <div id="collapse<?php echo $id?>" class="panel-collapse collapse" style="height: auto;">
        <div class="panel-body">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active"><a href="#p1_details<?php echo $id?>" data-toggle="tab">Details</a></li>
                <li><a href="#p1_organization<?php echo $id?>" data-toggle="tab">Organization</a></li>
                <li><a href="#p1_education<?php echo $id?>" data-toggle="tab">Education</a></li>
                <li><a href="#p1_extendedDetails<?php echo $id?>" data-toggle="tab">Extended Details</a></li>
                <li><a href="#p1_degrees<?php echo $id?>" data-toggle="tab">Degrees</a></li>
                <li><a href="#p1_unitDetails<?php echo $id?>" data-toggle="tab">Unit Details</a></li>
                <li><a href="#p1_proposalPersonCertification<?php echo $id?>" data-toggle="tab">Proposal Certification</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="p1_details<?php echo $id?>">
                    <h4>Details</h4>
                    <form class="form-horizontal" role="form">
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-6" >
                            <div class="form-group">
                                <label for="role<?php echo $id?>" class="col-md-4 col-sm-4 col-xs-4 control-label">Proposal Role:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <select name="role" id="role<?php echo $id?>" tabindex="0" style="">
                                        <option value="">select</option>
                                        <option value="copi" <?php echo $copiSelected;?>>Co-Investigator</option>
                                        <option value="pi" <?php echo $piSelected?>>Principal Investigator</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="full_name" class="col-md-4 col-sm-4 col-xs-4 control-label">Full Name:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="text" class="form-control" id="full_name" value="<?php echo $persons[$id]['name'];?>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="user" class="col-md-4 col-sm-4 col-xs-4 control-label">User Name:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="text" class="form-control" id="user" value="<?php echo $persons[$id]['user'];?>">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-6" >
                            <div class="form-group">
                                <label for="first" class="col-md-4 col-sm-4 col-xs-4 control-label">First Name:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="text" class="form-control" id="first" value="<?php echo $persons[$id]['first'];?>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="last" class="col-md-4 col-sm-4 col-xs-4 control-label">Last Name:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="text" class="form-control" id="last" value="<?php echo $persons[$id]['last'];?>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="middle" class="col-md-4 col-sm-4 col-xs-4 control-label">Middle Name:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="text" class="form-control" id="middle" value="<?php echo $persons[$id]['middle'];?>">
                                </div>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="tab-pane" id="p1_organization<?php echo $_SESSION['personnelId']?>">
                    <h4>Organization</h4>
                    <form class="form-horizontal" role="form">
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-6" >
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Email Address:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Office Phone:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Fax:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Pager:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Mobile:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Primary Title:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Directory Title:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Home Unit:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Division:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">eRA Commons User Name:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Office Location:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-6" >
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Secondary Office Location:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Address Line 1:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Address Line 2:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Address Line 3:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">City:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">County:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <select name="document.developmentProposalList[0].proposalPersons[0].countryCode" tabindex="28" onchange="javascript: loadStates(this.options[this.selectedIndex].value, 'document.developmentProposalList[0].proposalPersons[0].state');return false" onblur="" id="document.developmentProposalList[0].proposalPersons[0].countryCode" style="" class="" title="Country">
                                        <option value="">select: </option>
                                        <option value="USA" selected="selected">United States</option>
                                        <option value="UKR">Ukraine</option>
                                        <option value="UGA">Uganda</option>
                                        <option value="UMI">United States Minor Outlying Islands</option>
                                        <option value="USA" selected="selected">United States</option>
                                        <option value="URY">Uruguay</option>
                                        <option value="UZB">Uzbekistan</option>
                                        <option value="VAT">Holy See (Vatican City State)</option>
                                        <option value="VCT">Saint Vincent And The Grenedines</option>
                                        <option value="VEN">Venezuela, Bolivarian Republic of</option>
                                        <option value="VGB">Virgin Islands, British</option>
                                        <option value="VIR">Virgin Islands, U.S.</option>
                                        <option value="VNM">Viet Nam</option>
                                        <option value="VUT">Vanuatu</option>
                                        <option value="WLF">Wallis and Futuna</option>
                                        <option value="WSM">Samoa</option>
                                        <option value="YEM">Yemen</option>
                                        <option value="MYT">Mayotte</option>
                                        <option value="ZAF">South Africa</option>
                                        <option value="ZMB">Zambia</option>
                                        <option value="ZWE">Zimbabwe</option>
                                        <option value="ZZZ">Other Countries</option>
                                        <option value="AND">Andorra</option>
                                        <option value="ARE">United Arab Emirates</option>
                                        <option value="AFG">Afghanistan</option>
                                        <option value="ATG">Antigua and Barbuda</option>
                                        <option value="AIA">Anguilla</option>
                                        <option value="ALB">Albania</option>
                                        <option value="ARM">Armenia</option>
                                        <option value="ANT">Netherlands Antilles</option>
                                        <option value="AGO">Angola</option>
                                        <option value="ATA">Antarctica</option>
                                        <option value="ARG">Argentina</option>
                                        <option value="ASM">American Samoa</option>
                                        <option value="AUT">Austria</option>
                                        <option value="AUS">Australia</option>
                                        <option value="ABW">Aruba</option>
                                        <option value="ALA">??land Islands</option>
                                        <option value="AZE">Azerbaijan</option>
                                        <option value="BIH">Bosnia and Herzegovina</option>
                                        <option value="BRB">Barbados</option>
                                        <option value="BGD">Bangladesh</option>
                                        <option value="BEL">Belgium</option>
                                        <option value="BFA">Burkina Faso</option>
                                        <option value="BGR">Bulgaria</option>
                                        <option value="BHR">Bahrain</option>
                                        <option value="BDI">Burundi</option>
                                        <option value="BEN">Benin</option>
                                        <option value="BLM">Saint Barth??lemy</option>
                                        <option value="BMU">Bermuda</option>
                                        <option value="BRN">Brunei Darussalam</option>
                                        <option value="BOL">Bolivia, Plurinational State Of</option>
                                        <option value="BRA">Brazil</option>
                                        <option value="BHS">Bahamas</option>
                                        <option value="BTN">Bhutan</option>
                                        <option value="BVT">Bouvet Island</option>
                                        <option value="BWA">Botswana</option>
                                        <option value="BLR">Belarus</option>
                                        <option value="BLZ">Belize</option>
                                        <option value="CAN">Canada</option>
                                        <option value="CCK">Cocos (Keeling) Islands</option>
                                        <option value="COD">Congo, The Democratic Republic Of The</option>
                                        <option value="CAF">Central African Republic</option>
                                        <option value="COG">Congo</option>
                                        <option value="CHE">Switzerland</option>
                                        <option value="CIV">C??te D'Ivoire</option>
                                        <option value="COK">Cook Islands</option>
                                        <option value="CHL">Chile</option>
                                        <option value="CMR">Cameroon</option>
                                        <option value="CHN">China</option>
                                        <option value="COL">Colombia</option>
                                        <option value="CRI">Costa Rica</option>
                                        <option value="CUB">Cuba</option>
                                        <option value="CPV">Cape Verde</option>
                                        <option value="CXR">Christmas Island</option>
                                        <option value="CYP">Cyprus</option>
                                        <option value="CZE">Czech Republic</option>
                                        <option value="DEU">Germany</option>
                                        <option value="DJI">Djibouti</option>
                                        <option value="DNK">Denmark</option>
                                        <option value="DMA">Dominica</option>
                                        <option value="DOM">Dominican Republic</option>
                                        <option value="DZA">Algeria</option>
                                        <option value="ECU">Ecuador</option>
                                        <option value="EST">Estonia</option>
                                        <option value="EGY">Egypt</option>
                                        <option value="ESH">Western Sahara</option>
                                        <option value="ERI">Eritrea</option>
                                        <option value="ESP">Spain</option>
                                        <option value="ETH">Ethiopia</option>
                                        <option value="FIN">Finland</option>
                                        <option value="FJI">Fiji</option>
                                        <option value="FLK">Falkland Islands (Malvinas)</option>
                                        <option value="FSM">Micronesia, Federated States Of</option>
                                        <option value="FRO">Faroe Islands</option>
                                        <option value="FRA">France</option>
                                        <option value="GAB">Gabon</option>
                                        <option value="GBR">United Kingdom</option>
                                        <option value="GRD">Grenada</option>
                                        <option value="GEO">Georgia</option>
                                        <option value="GUF">French Guiana</option>
                                        <option value="GGY">Guernsey</option>
                                        <option value="GHA">Ghana</option>
                                        <option value="GIB">Gibraltar</option>
                                        <option value="GRL">Greenland</option>
                                        <option value="GMB">Gambia</option>
                                        <option value="GIN">Guinea</option>
                                        <option value="GLP">Guadeloupe</option>
                                        <option value="GNQ">Equatorial Guinea</option>
                                        <option value="GRC">Greece</option>
                                        <option value="SGS">South Georgia and South Sandwich Islands</option>
                                        <option value="GTM">Guatemala</option>
                                        <option value="GUM">Guam</option>
                                        <option value="GNB">Guinea-Bissau</option>
                                        <option value="GUY">Guyana</option>
                                        <option value="HKG">Hong Kong</option>
                                        <option value="HMD">Heard and McDonald Islands</option>
                                        <option value="HND">Honduras</option>
                                        <option value="HRV">Croatia</option>
                                        <option value="HTI">Haiti</option>
                                        <option value="HUN">Hungary</option>
                                        <option value="IDN">Indonesia</option>
                                        <option value="IRL">Ireland</option>
                                        <option value="ISR">Israel</option>
                                        <option value="IMN">Isle of Man</option>
                                        <option value="IND">India</option>
                                        <option value="IOT">British Indian Ocean Territory</option>
                                        <option value="IRQ">Iraq</option>
                                        <option value="IRN">Iran, Islamic Republic Of</option>
                                        <option value="ISL">Iceland</option>
                                        <option value="ITA">Italy</option>
                                        <option value="JEY">Jersey</option>
                                        <option value="JAM">Jamaica</option>
                                        <option value="JOR">Jordan</option>
                                        <option value="JPN">Japan</option>
                                        <option value="KEN">Kenya</option>
                                        <option value="KGZ">Kyrgyzstan</option>
                                        <option value="KHM">Cambodia</option>
                                        <option value="KIR">Kiribati</option>
                                        <option value="COM">Comoros</option>
                                        <option value="KNA">Saint Kitts And Nevis</option>
                                        <option value="PRK">Korea, Democratic People's Republic Of</option>
                                        <option value="KOR">Korea, Republic of</option>
                                        <option value="KWT">Kuwait</option>
                                        <option value="CYM">Cayman Islands</option>
                                        <option value="KAZ">Kazakhstan</option>
                                        <option value="LAO">Lao People's Democratic Republic</option>
                                        <option value="LBN">Lebanon</option>
                                        <option value="LCA">Saint Lucia</option>
                                        <option value="LIE">Liechtenstein</option>
                                        <option value="LKA">Sri Lanka</option>
                                        <option value="LBR">Liberia</option>
                                        <option value="LSO">Lesotho</option>
                                        <option value="LTU">Lithuania</option>
                                        <option value="LUX">Luxembourg</option>
                                        <option value="LVA">Latvia</option>
                                        <option value="LBY">Libyan Arab Jamahiriya</option>
                                        <option value="MAR">Morocco</option>
                                        <option value="MCO">Monaco</option>
                                        <option value="MDA">Moldova, Republic of</option>
                                        <option value="MNE">Montenegro</option>
                                        <option value="MAF">Saint Martin</option>
                                        <option value="MDG">Madagascar</option>
                                        <option value="MHL">Marshall Islands</option>
                                        <option value="MKD">Macedonia, the Fmr. Yugoslav Republic Of</option>
                                        <option value="MLI">Mali</option>
                                        <option value="MMR">Myanmar</option>
                                        <option value="MNG">Mongolia</option>
                                        <option value="MAC">Macao</option>
                                        <option value="MNP">Northern Mariana Islands</option>
                                        <option value="MTQ">Martinique</option>
                                        <option value="MRT">Mauritania</option>
                                        <option value="MSR">Montserrat</option>
                                        <option value="MLT">Malta</option>
                                        <option value="MUS">Mauritius</option>
                                        <option value="MDV">Maldives</option>
                                        <option value="MWI">Malawi</option>
                                        <option value="MEX">Mexico</option>
                                        <option value="MYS">Malaysia</option>
                                        <option value="MOZ">Mozambique</option>
                                        <option value="NAM">Namibia</option>
                                        <option value="NCL">New Caledonia</option>
                                        <option value="NER">Niger</option>
                                        <option value="NFK">Norfolk Island</option>
                                        <option value="NGA">Nigeria</option>
                                        <option value="NIC">Nicaragua</option>
                                        <option value="NLD">Netherlands</option>
                                        <option value="NOR">Norway</option>
                                        <option value="NPL">Nepal</option>
                                        <option value="NRU">Nauru</option>
                                        <option value="NIU">Niue</option>
                                        <option value="NZL">New Zealand</option>
                                        <option value="OMN">Oman</option>
                                        <option value="PAN">Panama</option>
                                        <option value="PER">Peru</option>
                                        <option value="PYF">French Polynesia</option>
                                        <option value="PNG">Papua New Guinea</option>
                                        <option value="PHL">Philippines</option>
                                        <option value="PAK">Pakistan</option>
                                        <option value="POL">Poland</option>
                                        <option value="SPM">Saint Pierre And Miquelon</option>
                                        <option value="PCN">Pitcairn</option>
                                        <option value="PRI">Puerto Rico</option>
                                        <option value="PSE">Palestinian Territory, Occupied</option>
                                        <option value="PRT">Portugal</option>
                                        <option value="PLW">Palau</option>
                                        <option value="PRY">Paraguay</option>
                                        <option value="QAT">Qatar</option>
                                        <option value="REU">R??union</option>
                                        <option value="ROU">Romania</option>
                                        <option value="SRB">Serbia</option>
                                        <option value="RUS">Russian Federation</option>
                                        <option value="RWA">Rwanda</option>
                                        <option value="SAU">Saudi Arabia</option>
                                        <option value="SLB">Solomon Islands</option>
                                        <option value="SYC">Seychelles</option>
                                        <option value="SDN">Sudan</option>
                                        <option value="SWE">Sweden</option>
                                        <option value="SGP">Singapore</option>
                                        <option value="SHN">St. Helena, Ascension, Tristan Da Cunha</option>
                                        <option value="SVN">Slovenia</option>
                                        <option value="SJM">Svalbard And Jan Mayen</option>
                                        <option value="SVK">Slovakia</option>
                                        <option value="SLE">Sierra Leone</option>
                                        <option value="SMR">San Marino</option>
                                        <option value="SEN">Senegal</option>
                                        <option value="SOM">Somalia</option>
                                        <option value="SUR">Suriname</option>
                                        <option value="STP">Sao Tome and Principe</option>
                                        <option value="SLV">El Salvador</option>
                                        <option value="SYR">Syrian Arab Republic</option>
                                        <option value="SWZ">Swaziland</option>
                                        <option value="TCA">Turks and Caicos Islands</option>
                                        <option value="TCD">Chad</option>
                                        <option value="ATF">French Southern Territories</option>
                                        <option value="TGO">Togo</option>
                                        <option value="THA">Thailand</option>
                                        <option value="TJK">Tajikistan</option>
                                        <option value="TKL">Tokelau</option>
                                        <option value="TLS">Timor-Leste</option>
                                        <option value="TKM">Turkmenistan</option>
                                        <option value="TUN">Tunisia</option>
                                        <option value="TON">Tonga</option>
                                        <option value="TUR">Turkey</option>
                                        <option value="TTO">Trinidad and Tobago</option>
                                        <option value="TUV">Tuvalu</option>
                                        <option value="TWN">Taiwan, Province Of China</option>
                                        <option value="TZA">Tanzania, United Republic of</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Country:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Postal Code:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">State:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <select name="document.developmentProposalList[0].proposalPersons[0].state" tabindex="30" onchange="" onblur="" id="document.developmentProposalList[0].proposalPersons[0].state" style="" class="" title="State">
                                        <option value=""></option>
                                        <option value="AL">US - ALABAMA</option>
                                        <option value="AK">US - ALASKA</option>
                                        <option value="AS">US - AMERICAN SAMOA</option>
                                        <option value="AZ">US - ARIZONA</option>
                                        <option value="AR">US - ARKANSAS</option>
                                        <option value="AA">US - ARMED FORCES AMERICAS (EXCEPT CANADA)</option>
                                        <option value="AE">US - ARMED FORCES EURO/CANADA/AFRICA/MID EAST</option>
                                        <option value="AP">US - ARMED FORCES PACIFIC</option>
                                        <option value="CA">US - CALIFORNIA</option>
                                        <option value="CO">US - COLORADO</option>
                                        <option value="CT">US - CONNECTICUT</option>
                                        <option value="DE">US - DELAWARE</option>
                                        <option value="DC">US - DISTRICT OF COLUMBIA</option>
                                        <option value="FM">US - FEDERATED STATES OF MICRONESIA</option>
                                        <option value="FL">US - FLORIDA</option>
                                        <option value="GA">US - GEORGIA</option>
                                        <option value="GU">US - GUAM</option>
                                        <option value="HI">US - HAWAII</option>
                                        <option value="ID">US - IDAHO</option>
                                        <option value="IL">US - ILLINOIS</option>
                                        <option value="IN">US - INDIANA</option>
                                        <option value="IA">US - IOWA</option>
                                        <option value="KS">US - KANSAS</option>
                                        <option value="KY">US - KENTUCKY</option>
                                        <option value="LA">US - LOUISIANA</option>
                                        <option value="ME">US - MAINE</option>
                                        <option value="MH">US - MARSHALL ISLANDS</option>
                                        <option value="MD">US - MARYLAND</option>
                                        <option value="MA" selected="selected">US - MASSACHUSETTS</option>
                                        <option value="MI">US - MICHIGAN</option>
                                        <option value="MN">US - MINNESOTA</option>
                                        <option value="MS">US - MISSISSIPPI</option>
                                        <option value="MO">US - MISSOURI</option>
                                        <option value="MT">US - MONTANA</option>
                                        <option value="NE">US - NEBRASKA</option>
                                        <option value="NV">US - NEVADA</option>
                                        <option value="NH">US - NEW HAMPSHIRE</option>
                                        <option value="NJ">US - NEW JERSEY</option>
                                        <option value="NM">US - NEW MEXICO</option>
                                        <option value="NY">US - NEW YORK</option>
                                        <option value="NC">US - NORTH CAROLINA</option>
                                        <option value="ND">US - NORTH DAKOTA</option>
                                        <option value="MP">US - NORTHERN MARIANA ISLANDS</option>
                                        <option value="OH">US - OHIO</option>
                                        <option value="OK">US - OKLAHOMA</option>
                                        <option value="OR">US - OREGON</option>
                                        <option value="--">US - OUT OF COUNTRY</option>
                                        <option value="PW">US - PALAU</option>
                                        <option value="PA">US - PENNSYLVANIA</option>
                                        <option value="PR">US - PUERTO RICO</option>
                                        <option value="RI">US - RHODE ISLAND</option>
                                        <option value="SC">US - SOUTH CAROLINA</option>
                                        <option value="SD">US - SOUTH DAKOTA</option>
                                        <option value="TN">US - TENNESSEE</option>
                                        <option value="TX">US - TEXAS</option>
                                        <option value="UT">US - UTAH</option>
                                        <option value="VT">US - VERMONT</option>
                                        <option value="VI">US - VIRGIN ISLANDS</option>
                                        <option value="VA">US - VIRGINIA</option>
                                        <option value="WA">US - WASHINGTON</option>
                                        <option value="WV">US - WEST VIRGINIA</option>
                                        <option value="WI">US - WISCONSIN</option>
                                        <option value="WY">US - WYOMING</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Percentage Effort:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Faculty:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="tab-pane" id="p1_education<?php echo $_SESSION['personnelId']?>">
                    <h4>Education</h4>
                    <form class="form-horizontal" role="form">
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-6" >
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Education Level:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Year Graduated:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Major:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-6" >
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Degree:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">School:</label>
                                <div class="col-md-8 col-sm-8 col-xs-8 ">
                                    <input type="email" class="form-control" id="asdf">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="tab-pane" id="p1_extendedDetails<?php echo $_SESSION['personnelId']?>">
                <h4>Extended Details</h4>
                <form class="form-horizontal" role="form">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-6" >
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Age by Fiscal Year:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Race:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Education Level:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Degree:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Major:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">KcPerson Id:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Is Handicapped:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Handicap Type:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Veteran:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Veteran Type:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Has Visa:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Visa Code:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Visa Type:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Visa Renewal Date:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-6" >
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Office Location:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Secondary Office Location:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">School:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Year Graduated:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Directory Department:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Primary Title:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Directory Title:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Is Vacation Accrual:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Is on Sabbatical:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">County:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Id Provided:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Id Verified:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Version Number:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="asdf" class="col-md-4 col-sm-4 col-xs-4 control-label">Citzenship Type:</label>
                            <div class="col-md-8 col-sm-8 col-xs-8 ">
                                <input type="email" class="form-control" id="asdf">
                            </div>
                        </div>
                    </div>
                </div>
                </form>
            </div>
            <div class="tab-pane" id="p1_degrees<?php echo $_SESSION['personnelId']?>">
                <h4>Degrees</h4>
                <form class="form-horizontal" role="form">
                <table class="table table-condensed table-smaller-text" id="table">
                    <thead>
                        <tr>
                            <th style="width:16px">&nbsp;</th>
                            <th> Type</th>
                            <th style="width:25%">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="text-align:center"><a href="#"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
                            <td><a href="#">Bachelor of Arts</a></td>
                            <td><a href="#">view/edit</a> | <a href="#">delete</a></td>
                        </tr>
                        <tr>
                            <td style="text-align:center"><a href="#"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
                            <td><a href="#">Master of Education</a></td>
                            <td><a href="#">view/edit</a> | <a href="#">delete</a></td>
                        </tr>
                        <tr>
                            <td style="text-align:center"><a href="aaa-compliance-view.html"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
                            <td><a href="#">Doctor of Humanities</a><a href="aaa-compliance-view.html"></a></td>
                            <td><a href="aaa-compliance-view.html">view/edit</a> | <a href="#">delete</a></td>
                        </tr>
                    </tbody>
                </table>
                <a class="btn btn-default btn-xs" id="" href="#"><span aria-hidden="true" class="icon-plus"></span> Add Degree</a>
                </form>
            </div>
            <div class="tab-pane" id="p1_unitDetails<?php echo $_SESSION['personnelId']?>">
                <h4>Unit Details</h4>
                <form class="form-horizontal" role="form">
                <table class="table table-condensed table-smaller-text" id="table">
                    <thead>
                        <tr>
                            <th> Unit Name</th>
                            <th style="width:25%">Unit Number</th>
                            <th style="width:25%">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>IND INST ON DISABILITY/COMMNTY</td>
                            <td>BL-IIDC</td>
                            <td><a href="#">delete</a></td>
                        </tr>
                        <tr>
                            <td>University</td>
                            <td>000001</td>
                            <td><a href="#">delete</a></td>
                        </tr>
                    </tbody>
                </table>
                <a class="btn btn-default btn-xs" id="" href="#"><span aria-hidden="true" class="icon-plus"></span> Add Unit</a>
                </form>
            </div>
            <div class="tab-pane" id="p1_proposalPersonCertification<?php echo $_SESSION['personnelId']?>">
                <h4>Proposal Certification</h4>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">Can you certify that the information here is all true, complete, and accurate to the best of your knowlege?</legend>
                        </div>
                        <div class="question-text">
                            <p>Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_1a" name="q_1_a" value="1" class="radio-choice" /> <label for="q_1_1a">Yes, all of the information here is correct, accurate, and true to the best of my knowledge.</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_1b" name="q_1_a" value="0" class="radio-choice" /> <label for="q_1_1b">No</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">Is there any potential for a perceived or real conflict of interest as defined in MIT's Policies and Procedures with regard to this proposal?</legend>
                        </div>
                        <div class="question-text">
                            <p>Is there any potential for a perceived or real conflict of interest as defined in MIT's Policies and Procedures with regard to this proposal?</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_2a" name="q_2_a" value="1" class="radio-choice" /> <label for="q_1_2a">Yes</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_2b" name="q_2_a" value="0" class="radio-choice" /> <label for="q_1_2b">No, there is not potential for perceived or real conflict of interest.</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">If this is a NIH/NSF proposal, have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?</legend>
                        </div>
                        <div class="question-text">
                            <p>If this is a NIH/NSF proposal, have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_3a" name="q_3_a" value="1" class="radio-choice" /> <label for="q_1_3a">Yes, I have submitted the required financial disclosures.</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_3b" name="q_3_a" value="0" class="radio-choice" /> <label for="q_1_3b">No</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">Have lobbying activities been conducted on behalf of this proposal?</legend>
                        </div>
                        <div class="question-text">
                            <p>Have lobbying activities been conducted on behalf of this proposal?</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_4a" name="q_4_a" value="1" class="radio-choice" /> <label for="q_1_4a">Yes</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_4b" name="q_4_a" value="0" class="radio-choice" /> <label for="q_1_4b">No, lobbying activities have not been conducted.</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</legend>
                        </div>
                        <div class="question-text">
                            <p>Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_5a" name="q_5_a" value="1" class="radio-choice" /> <label for="q_1_5a">Yes</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_5b" name="q_5_a" value="0" class="radio-choice" /> <label for="q_1_5b">No, I am eligible for all transactions.</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">Are you familiar with the requirements of the Procurement Liabilities Act?</legend>
                        </div>
                        <div class="question-text">
                            <p>Are you familiar with the requirements of the Procurement Liabilities Act?</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_6a" name="q_6_a" value="1" class="radio-choice" /> <label for="q_1_6a">Yes, I am familiar with all requirements.</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_6b" name="q_6_a" value="0" class="radio-choice" /> <label for="q_1_6b">No</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="question-set">
                    <fieldset class="question">
                        <div class="question-title">
                            <legend class="off-screen">Is this a new full application related to a submission of a preliminary application?</legend>
                        </div>
                        <div class="question-text">
                            <p>Is this a new full application related to a submission of a preliminary application?</p>
                        </div>
                        <div class="question-response">
                            <div class="question-reponse-choices">
                                <div class="choice">
                                    <input type="radio" id="q_1_7a" name="q_7_a" value="1" class="radio-choice" /> <label for="q_1_7a">Yes.</label>
                                </div>
                                <div class="choice">
                                    <input type="radio" id="q_1_7b" name="q_7_a" value="0" class="radio-choice" /> <label for="q_1_7b">No, this new application is not related to another preliminary application.</label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
