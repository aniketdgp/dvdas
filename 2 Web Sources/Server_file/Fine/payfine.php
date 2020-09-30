<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $policename = $_POST['policename'];
 $reason = $_POST['reason'];
 $fineamount =$_POST['fineamount'];

 $CheckSQLuser = "SELECT * FROM useraccount WHERE username ='$username'";
 $checkuser = mysqli_fetch_array(mysqli_query($con,$CheckSQLuser));
 

 $GetFineAmount = "Select fineamount from userfine where username = '$username'";
 $FineArray = mysqli_query($con,$GetFineAmount);
 $TotalDue = 0;
 
 while($row = mysqli_fetch_array($FineArray)){
 
  $TotalDue = $TotalDue+$row['fineamount'];
  
}

 $diff = $fineamount+$TotalDue;
 $len = strlen($TotalDue);
 $td = substr($TotalDue,1,$len);


   
 if(isset($checkuser) && $TotalDue+$fineamount<=0){
 $Sql_Query = "Insert into userfine(username,policename,reason,fineamount)values('$username','$policename','$reason','$fineamount')";
 if(mysqli_query($con,$Sql_Query)){
 echo "You Paid Rs ".$fineamount." as Fine";
 } else {
 echo 'Something went wrong';
 }
 }else if($TotalDue==0){
 echo 'You Don\'t Have Any Existing Fine';
 }
 else{
 echo 'You are Paying Rs '.$diff." more You need to Pay Rs ".$td;
 }
 

    
}
   
 ?>