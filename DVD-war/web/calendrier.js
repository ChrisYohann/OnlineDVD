	
       function setStyle(id,style,value)
{
    id.style[style] = value;
}
function opacite(el,opacity)
{
    setStyle(el,"filter:","alpha(opacity="+opacity+")");
    setStyle(el,"-moz-opacity",opacity/100);
    setStyle(el,"-khtml-opacity",opacity/100);
    setStyle(el,"opacity",opacity/100);
}




function date_aujourdui(nb_de_jour_apres) 
{

var j = 0;
var date = new Date();
    var jour = date.getDate() ;
    var moi = date.getMonth();
	var day = date.getDay() ;
	day = (day + nb_de_jour_apres ) % 7 ;
	while ( j==0 ) 
	{
	
	if ( moi == 1 ||3||5||7||8||10||12 ) 
	{
	
	if ( jour + nb_de_jour_apres  > 31 ) {
	nb_de_jour_apres= nb_de_jour_apres - 31 + jour  ;
	moi += 1;
	jour = 0;
	}
	
	else  	{
		jour = jour + nb_de_jour_apres;
		j=1;
		nb_de_jour_apres=0;
	}
	
	}
	
	if ( moi == 4||6||9|11 ) 
	{
	if ( jour + nb_de_jour_apres  > 30 ) {
	nb_de_jour_apres= nb_de_jour_apres - 30 + jour  ;
	moi += 1;
	jour = 0;
	}
	
	else  	{
		jour = jour + nb_de_jour_apres;
		j=1;
		nb_de_jour_apres=0;
		
	}
	
	}
	
	}
	mois = new Array('Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre', 'Novembre', 'D&eacute;cembre');
	var tab_jour=new Array("Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi");
   document.write(tab_jour[day] + " " + jour +" "+ mois[moi] ) ;

}





function calendrier()
{
    var date = new Date();
    var jour = date.getDate();
    var moi = date.getMonth();
    var annee = date.getYear();
    if(annee<=200)
    {
        annee += 1900;
    }
    mois = new Array('Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre', 'Novembre', 'D&eacute;cembre');
    jours_dans_moi = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if(annee%4 == 0 && annee!=1900)
    {
        jours_dans_moi[1]=29;
    }
    total = jours_dans_moi[moi];
    var date_aujourdui = jour+' '+mois[moi]+' '+annee;
    dep_j = date;
    dep_j.setDate(1);
    if(dep_j.getDate()==2)
    {
        dep_j=setDate(0);
    }
    dep_j = dep_j.getDay();
    document.write('<table class="cal_calendrier" onload="opacite(document.getElementById(\'cal_body\'),20);"><tbody id="cal_body"><tr><th colspan="8">'+ "Nous sommes le " +date_aujourdui+'</th></tr>');
    document.write('<tr class="cal_j_semaines"><th>Dim</th><th>Lun</th><th>Mar</th><th>Mer</th><th>Jeu</th><th>Ven</th><th>Sam</th></tr><tr>');
    sem = 0;
    for(i=1;i<=dep_j;i++)
    {
        document.write('<td class="cal_jours_av_ap">'+(jours_dans_moi[moi-1]-dep_j+i)+'</td>');
        sem++;
    }
    for(i=1;i<=total;i++)
    {
        if(sem==0)
        {
            document.write('<tr>');
        }
        if(jour==i)
        {
            document.write('<td class="cal_aujourdhui">'+i+'</td>');
        }
        else
        {
            document.write('<td class="cal_pasaujour">'+i+'</td>');
        }
        sem++;
        if(sem==7)
        {
            document.write('</tr>');
            sem=0;
        }
    }
    for(i=1;sem!=0;i++)
    {
        document.write('<td class="cal_jours_av_ap">'+i+'</td>');
        sem++;
        if(sem==7)
        {
            document.write('</tr>');
            sem=0;
        }
    }
    document.write('</tbody></table>');
    opacite(document.getElementById('cal_body'),70);
    return true;
}