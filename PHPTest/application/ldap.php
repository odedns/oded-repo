<?php 
// basic sequence with LDAP is connect, bind, search, interpret search 
// result, close connection 

$user  = "Adquery086@SAP.CORP";
$pass = "TwiceX21";  // associated password
$port = 398;
//$host = "sapwdf12.wdf.sap.corp";
$host = "ldap://10.17.120.218";
$dnbase =  "DC=sap,DC=corp";
//$filter = "cn=i070659,cn=Users,dc=sap,dc=corp";
$filter ="(&(objectclass=user)(samAccountName=c5076270))";


echo "<h3>LDAP query test</h3>"; 
echo "Connecting ..."; 
$ds=ldap_connect($host,$port);  // must be a valid LDAP server! 
echo "connect result is ".$ds."<p>"; 


ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3);
ldap_set_option($ds, LDAP_OPT_REFERRALS, 0);


if ($ds) { 
    echo "Binding ..."; 
    $r=ldap_bind($ds,$user,$pass);     // this is an "anonymous" bind, typically
    
    if(!$r) {
    	echo("msg:'".ldap_error($ds));
    	echo "bind failed....";
    	ldap_close($ds);
    	 
    	die;
    }
                           // read-only access 
    echo "Bind result is ".$r."\n"; 

    echo "Searching  ..."; 
    // Search surname entry 
    $sr=ldap_search($ds,$dnbase,$filter);   
    echo "Search result is ".$sr."\n"; 

    echo "Number of entires returned is ".ldap_count_entries($ds,$sr)."\n"; 

    echo "Getting entries ...\n"; 
    $info = ldap_get_entries($ds, $sr); 
    //var_dump($info);
    echo "Data for ".$info["count"]." items returned:\n"; 

    for ($i=0; $i<$info["count"]; $i++  ) { 
        echo "dn is: ". $info[$i]["dn"] ."<br>"; 
        echo "first cn entry is: ". $info[$i]["cn"][0] ."\n"; 
        echo "first email entry is: ". $info[$i]["mail"][0] ."\n"; 
    } 

    echo "Closing connection\n"; 
    ldap_close($ds); 

} else { 
    echo "<h4>Unable to connect to LDAP server</h4>\n"; 
} 
?>