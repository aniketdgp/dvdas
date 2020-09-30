<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $policename = $_POST['policename'];
 $reason = $_POST['reason'];
 $fineamount = $_POST['fineamount'];

 $CheckSQLuser = "SELECT * FROM useraccount WHERE username ='$username'";
 $checkuser = mysqli_fetch_array(mysqli_query($con,$CheckSQLuser));
 
 $CheckSQLpolice = "SELECT * FROM userpolice WHERE username ='$policename'";
 $checkpolice = mysqli_fetch_array(mysqli_query($con,$CheckSQLpolice));
 
 
 if(isset($checkuser) && isset($checkpolice)){

 $Sql_Query = "Insert into userfine(username,policename,reason,fineamount)values('$username','$policename','$reason','-$fineamount')";
 if(mysqli_query($con,$Sql_Query)){
 echo 'You fined '.$username.' with amount Rs :'.$fineamount;
 } else {
 echo 'Something went wrong';
 }
 }else{
 echo $username."dosen't Exists in our Databases";
 }
 
 
    
 }
   
   
 ?>
