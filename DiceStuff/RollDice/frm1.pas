unit frm1;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  Buttons;

type

  { TForm1 }

  TForm1 = class(TForm)
    btnRoll: TBitBtn;
    Image1: TImage;
    Image2: TImage;
    ImageList1: TImageList;
    procedure btnRollClick(Sender: TObject);
  private
    { private declarations }
  public
    { public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.lfm}

{ TForm1 }

procedure TForm1.btnRollClick(Sender: TObject);
var
  bmp: TBitmap;
  rand1, rand2: Integer;
begin

  // get the random number for dice
  Randomize;
  rand1 := Random(6);

  bmp := TBitmap.Create;
  try
    ImageList1.GetBitmap(rand1, bmp);
    Image1.Picture.Assign(bmp);
  finally
    bmp.Free;
  end;

  // now to second dice
  rand2 := Random(6);

  bmp := TBitmap.Create;
  try
    ImageList1.GetBitmap(rand2, bmp);
    Image2.Picture.Assign(bmp);
  finally
    bmp.Free;
  end;

end;

end.

