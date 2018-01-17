package main.object.fps;

import piengine.core.time.manager.TimeManager;
import piengine.object.asset.domain.GuiAsset;
import piengine.object.asset.plan.GuiRenderAssetContext;
import piengine.object.asset.plan.GuiRenderAssetContextBuilder;
import piengine.visual.writing.font.domain.Font;
import piengine.visual.writing.font.manager.FontManager;
import piengine.visual.writing.text.domain.Text;
import piengine.visual.writing.text.domain.TextConfiguration;
import piengine.visual.writing.text.manager.TextManager;
import puppeteer.annotation.premade.Wire;

public class FpsAsset extends GuiAsset<FpsAssetArgument> {

    private final FontManager fontManager;
    private final TextManager textManager;
    private final TimeManager timeManager;

    private Font font;
    private Text fpsText;

    @Wire
    public FpsAsset(final FontManager fontManager, final TextManager textManager,
                    final TimeManager timeManager) {
        this.fontManager = fontManager;
        this.textManager = textManager;
        this.timeManager = timeManager;
    }

    @Override
    public void initialize() {
        font = fontManager.supply("candara");
        fpsText = textManager.supply(TextConfiguration.textConfig().withFont(font), this);
        fpsText.setPosition(0.85f, 0.85f, 0);
    }

    @Override
    public void update(final double delta) {
        textManager.update(fpsText, TextConfiguration.textConfig().withFont(font).withFontSize(2).withText("FPS: " + timeManager.getFPS()));
    }

    @Override
    public GuiRenderAssetContext getAssetContext() {
        return GuiRenderAssetContextBuilder.create()
                .loadTexts(fpsText)
                .build();
    }
}
