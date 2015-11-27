function handleComplete(xhr, status, args, nomeJanela) {
    if (args.validationFailed) {
        //alert("Campos obrigatorios");
    } else {

        PF(nomeJanela).hide();
    }
}