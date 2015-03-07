<?php 
// basic sequence with LDAP is connect, bind, search, interpret search 
// result, close connection 

//$user  = "i070659@global.corp.sap";
//$user = "C1234567@global.corp.sap";
$user= "CN=C5182577,OU=C,OU=Identities,DC=global,DC=corp,DC=sap";

//$pass = "Guss2013";  // associated password
$pass= "ADmigr8tion";
$port = 389;
//$port = 636;
//$host = "sapwdf12.wdf.sap.corp";
//$host = "ldap://10.17.120.218";
$host = "ldap://dewdfils.global.corp.sap";
$dnbase =  "DC=sap,DC=corp";
//$filter = "cn=i070659,cn=Users,dc=sap,dc=corp";
$filter ="(&(objectclass=user)(cn=i070659))";


echo "<h3>LDAP query test</h3>"; 
echo "Connecting ..."; 
$ds=ldap_connect($host,$port);  // must be a valid LDAP server! 
echo "connect result is ".$ds."<p>"; 


ldap_set_option($ds, LDAP_OPT_PROTOCOL_VERSION, 3);
ldap_set_option($ds, LDAP_OPT_REFERRALS, 0);


if ($ds) { 
    echo "Binding ...\n"; 
    $r=ldap_bind($ds,$user,$pass);     // this is an "anonymous" bind, typically
    
    if(!$r) {
    	echo("\nmsg:'".ldap_error($ds));
    	echo "\nbind failed....\n";
    	ldap_close($ds);
    	 
    	die;
    }
                           // read-only access 
    echo "Bind result is ".$r."\n"; 

   
    echo "Closing connection\n"; 
    ldap_close($ds); 

} else { 
    echo "<h4>Unable to connect to LDAP server</h4>\n"; 
} 
?>